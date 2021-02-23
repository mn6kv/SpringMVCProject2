package ru.itis.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailsGeneratorFreemarkerImpl implements MailsGenerator {

    @Autowired
    Configuration configuration;

    @Override
    public String getEmailToConfirm(String serverUrl, String code) {

        Template mailTemplate;
        try {
            mailTemplate = configuration.getTemplate("confirm_mail.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Map<String, String> attributes = new HashMap<>();
        attributes.put("server_url", serverUrl);
        attributes.put("confirm_code", code);

        StringWriter stringWriter = new StringWriter();
        try {
            mailTemplate.process(attributes, stringWriter);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        return stringWriter.toString();
    }
}
