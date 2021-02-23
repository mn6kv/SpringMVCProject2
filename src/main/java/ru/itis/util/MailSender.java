package ru.itis.util;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void sendMail(String to, String subject, String from, String text);
}
