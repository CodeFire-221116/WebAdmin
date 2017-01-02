package ua.com.codefire.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by human on 12/27/16.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ContextConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        Properties props = new Properties();
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));

        sender.setJavaMailProperties(props);
        sender.setHost(env.getProperty("mail.smtp.host"));
        sender.setPort(env.getProperty("mail.smtp.port", Integer.class));
        sender.setUsername(env.getProperty("mail.smtp.username"));
        sender.setPassword(env.getProperty("mail.smtp.password"));

        return sender;
    }
}
