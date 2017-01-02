package ua.com.codefire.cms.web.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by human on 12/29/16.
 */
@Component
public class MailComponent {

    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send mail to
     *
     * @param subject message subject.
     * @param message message body.
     * @param to      recipients emails.
     */
    public void sendMail(String subject, String message, InternetAddress... to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        mailMessage.setFrom(new InternetAddress(env.getProperty("mail.smtp.username"), "Web CMS"));
        mailMessage.setSubject(subject);
        mailMessage.setContent(message, "text/html");
        mailMessage.setRecipients(Message.RecipientType.TO, to);

        mailSender.send(mailMessage);
    }

}
