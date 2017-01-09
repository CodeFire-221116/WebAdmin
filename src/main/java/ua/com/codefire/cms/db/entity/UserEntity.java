package ua.com.codefire.cms.db.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
    //    @NotEmpty
//    @NotNull
    @NotBlank
    @Size(min = 6, max = 16)
    @Column(name = "user_name")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="users_articles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")},
            inverseJoinColumns = @JoinColumn(name="article_id", referencedColumnName="article_id"))
    private Collection<ArticleEntity> articles;

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

    public Collection<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
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

        UserEntity user = (UserEntity) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
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
