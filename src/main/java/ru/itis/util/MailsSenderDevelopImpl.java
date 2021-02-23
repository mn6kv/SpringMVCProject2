package ru.itis.util;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Profile("dev")
@Component
public class MailsSenderDevelopImpl implements MailSender {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        System.out.println(msg);
    }
}
