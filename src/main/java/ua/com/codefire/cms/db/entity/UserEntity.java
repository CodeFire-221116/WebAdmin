package ua.com.codefire.cms.db.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by human on 12/6/16.
 */
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(min = 4, max = 16)
    @Column(name = "user_name", unique = true)
    private String username;
    // MD5 HASH
    @NotBlank
    @Size(min = 8, max = 40)
    @Column(name = "user_pass", length = 32)
    private String password;
    @Email
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_access_lvl")
    @Enumerated(EnumType.ORDINAL)
    private AccessLevel accessLvl;
    @Column(name = "user_email_valid")
    private Long emailKey;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(
            name="users_articles",
            joinColumns =
                    {@JoinColumn(name="user_id", referencedColumnName="user_id", foreignKey = @ForeignKey(name = "user_id_fk"))},
            inverseJoinColumns =
                    {@JoinColumn(name="article_id", referencedColumnName="article_id", foreignKey = @ForeignKey(name = "article_id_fk"))})//},
    private Set<ArticleEntity> articles;

    public UserEntity() {
    }

    public UserEntity(String username, String notEncryptedPassword) {
        this.username = username;
        this.password = DigestUtils.md5Hex(notEncryptedPassword);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(Long emailKey) {
        this.emailKey = emailKey;
    }

    public AccessLevel getAccessLvl() {
        return accessLvl;
    }

    public void setAccessLvl(AccessLevel accessLvl) {
        this.accessLvl = accessLvl;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public boolean checkPassword(String notEncryptedPassword) {
        return DigestUtils.md5Hex(notEncryptedPassword).equals(password);
    }

    /**
     * function for updating password by user
     *
     * @param notEncryptedPassword New not encrypted password.
     */
    public void updatePassword(String notEncryptedPassword) {
        this.password = DigestUtils.md5Hex(notEncryptedPassword);
    }

    public Boolean canChangeAccessLvl(AccessLevel userAccessLevel) {
        if (getAccessLvl() == AccessLevel.HyperAdmin) {
            return true;
        } else if (getAccessLvl() == userAccessLevel) {
            return false;
        } else if (getAccessLvl() == AccessLevel.Admin && userAccessLevel == AccessLevel.User) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (accessLvl != that.accessLvl) return false;
        return emailKey != null ? emailKey.equals(that.emailKey) : that.emailKey == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accessLvl != null ? accessLvl.hashCode() : 0);
        result = 31 * result + (emailKey != null ? emailKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                '}';
    }

    public enum AccessLevel {
        HyperAdmin,
        Admin,
        User
    }
}
