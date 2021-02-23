package ru.itis.util;

public interface MailsGenerator {
    String getEmailToConfirm(String serverUrl, String code);
}
