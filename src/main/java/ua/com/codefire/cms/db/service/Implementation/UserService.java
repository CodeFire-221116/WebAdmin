package ua.com.codefire.cms.db.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.db.repo.UserEntityRepository;
import ua.com.codefire.cms.model.ExternalServicesAccounts;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IUserService variable in case of need in entity-specific
 * methods and in the ICommonService(UserEntity) variable in case of need in CRUD operations.
 * The creation has to be with the help of @Autowired annotation
 * @author ankys
 */
@Service
public class UserService implements IUserService {
    private UserEntityRepository userRepo;
    private static final Logger LOGGER = Logger.getLogger(UserEntityRepository.class.getName());

    @Autowired
    public UserService(UserEntityRepository pageEntityRepository) {
        this.userRepo = pageEntityRepository;
    }

    @Override
    public Long create(UserEntity objToCreate) {
        try {
            UserEntity savedPage = userRepo.saveAndFlush(objToCreate);
            return savedPage.getId();
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such user already exists.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    @Override
    public UserEntity read(Long idToFind) {
        return userRepo.findOne(idToFind);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        try {
            userRepo.delete(objToDelete);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No user found by such id.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting user.", ex);
        }
        return false;
    }

    @Override
    public Boolean update(UserEntity objToCreate) {
        try {
            userRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such user found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while updating user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    @Override
    public List<UserEntity> getAllEntities() {
        return userRepo.findAll();
    }

    @Override
    public Long getAmountOfEntities() {
        return userRepo.count();
    }

    @Override
    public UserEntity getUserByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public Boolean ifUserRegistered(String name, String password) {
        if (name == null) {
            return null;
        }

        UserEntity userByName = getUserByName(name);

        if (userByName == null) {
            return null;
        }

        if (password == null) {
            return false;
        }

        return userByName.checkPassword(password);
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
                new Authenticator() {
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
}
