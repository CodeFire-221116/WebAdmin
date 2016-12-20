package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.abstraction.IUserRepo;
import ua.com.codefire.cms.model.ExternalServicesAccounts;
import ua.com.codefire.cms.utils.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by human on 12/6/16.
 */
/**
 * An implementation of entity-specific Repository Interface.
 * The object of this class needs to be put in the IUserRepo variable in case of need in entity-specific
 * methods and in the ICommonRepo(UserEntity) variable in case of need in CRUD operations.
 * Used in UserService in order to communicate with DataBase
 */
public class UserRepo implements IUserRepo {
    private static final Logger LOGGER = Logger.getLogger(UserRepo.class.getName());
    private EntityManagerHelper entityManagerHelper;

    public UserRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(UserEntity objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Such user already exists.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while creating new user.", ex);
        }
        return null;
    }

    @Override
    public UserEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(UserEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No page found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching for page.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching for page.", ex);
        }
        return null;
    }

    @Override
    public Boolean update(UserEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No such user found.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while updating the user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while updating the user.", ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            UserEntity userToDelete = entityManagerHelper.find(UserEntity.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(userToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No user found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching and deleting user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching and deleting user.", ex);
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllEntities() {
        List<UserEntity> users = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user", UserEntity.class);
            users = (List<UserEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving users from db.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while retrieving all users from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving users from db.", ex);
        }
        return users;
    }

    @Override
    public UserEntity getUserByName(String name) {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user WHERE user.username = :userName", UserEntity.class);
            query.setMaxResults(1);
            query.setParameter("userName", name);
            return (UserEntity) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving user by name from db.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while retrieving user by name.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving user by name from db.", ex);
        }
        return null;
    }

    @Override
    public Boolean sendValidationEmail(Long id) {
        UserEntity userEntity = read(id);
        if (userEntity.getEmail().isEmpty()) {
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ExternalServicesAccounts.GOOGLE_ACCOUNT_NAME,
                                ExternalServicesAccounts.GOOGLE_ACCOUNT_PASSWORD);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ExternalServicesAccounts.GOOGLE_ACCOUNT_NAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEntity.getEmail()));

            message.setSubject("Please verify your email address");

            //message.setText("< SET THE VALIDATION LETTER BODY HERE!!! >");
            //message.setContent(getUtils.readFileAsString("/", Charset.forName("utf8")), "text/html; charset=utf-8");
            String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n" +
                    "        \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title>Email verification letter</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css\"/>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <h3 class=\"page-header\">Please verify your email address</h3>\n" +
                    "    <h4>Hi [username],</h4>\n" +
                    "    <h4>Please\n" +
                    "        <mark>verify</mark>\n" +
                    "        your email address to finish your account registration.\n" +
                    "    </h4>\n" +
                    "    <br>\n" +
                    "    <a class=\"btn btn-primary btn-lg\" href=\"http://localhost:8080/?umv=[KeyCodeForReplacement]\">Verify my email address</a>\n" +
                    "    <hr>\n" +
                    "    <p>Cheers,<br>\n" +
                    "    Your WebAdmin team</p>\n" +
                    "</div>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                    "</html>";


            Long time = new Date().getTime();
            userEntity.setEmailKey(time);
            update(userEntity);
            String strId = id.toString();
            String key = strId + time + String.format("%02d", strId.length());
            System.out.printf("id:%s time:%d len:%s\n", strId, time, String.format("%02d", strId.length()));
            System.out.printf("key:%s\n", key);

            message.setContent(content.replace("[KeyCodeForReplacement]", key), "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException ex) {
            //throw new RuntimeException(e);
            LOGGER.log(Level.SEVERE, "Unexpected exception, while sending validation email.", ex);
            return false;
//        } catch (IOException ex) {
//            //throw new RuntimeException(e);
//            LOGGER.log(Level.SEVERE, "Unexpected exception, while setting content of validation email.", ex);
//            return false;
        }

        return true;
    }

    @Override
    public Boolean validateEmail(String validationCode) {

        if (validationCode.isEmpty()) return false;

        String valCode = validationCode.trim();
        System.out.println("valCode:"+valCode);

        int valCodeLength = valCode.length();
        System.out.println("valCodeLength:"+valCodeLength);

        int symCol = Integer.parseInt(valCode.substring(valCodeLength-2, valCodeLength));
        if (symCol < 1) return false;

        System.out.println("symCol:"+symCol);

        long id = Long.parseLong(valCode.substring(0, symCol));
        System.out.println("id:"+id);

        long key = Long.parseLong(valCode.substring(symCol, valCodeLength-2));
        System.out.println("key:"+key);

        // check for valid id and key not earlier than 72 hours
        System.out.printf("id:%d code:%d\n", id, key);
        if (id < 1 && key < (new Date().getTime() - 72*60*60)) {
            return false;
        }

        UserEntity user = read(id);

        if (user == null) {
            return false;
        }

        if (user.getEmailKey() == 1) {
            return false;
        } else if (user.getEmailKey() != key) {
            user.setEmailKey((long)0);
            update(user);
            return false;
        }

        user.setEmailKey((long)1);
        update(user);

        return true;
    }

    @Override
    public Long getAmountOfEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT COUNT(users.id) FROM UserEntity users", Long.class);
            return (Long) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving amount of user from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving amount of user from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving amount of user from db.", ex);
        }
        return null;
    }
}
