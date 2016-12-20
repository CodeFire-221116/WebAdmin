package ua.com.codefire.cms.db.entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "user_name")
    private String username;
    // MD5 HASH
    @Column(name = "user_pass", length = 32)
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_email_valid")
    private Long emailKey;

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

    public boolean checkPassword(String notEncryptedPassword) {
        return DigestUtils.md5Hex(notEncryptedPassword).equals(password);
    }

    /**
     * function for updating password by user
     * @param notEncryptedPassword New not encrypted password.
     */
    public void updatePassword(String notEncryptedPassword) {
        this.password = DigestUtils.md5Hex(notEncryptedPassword);
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
}
