package com.example.email.service;

import java.time.LocalDate;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private TemplateEngine templateEngine;
    private static Context context;
    private JavaMailSender emailSender;

    private Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.templateEngine = initializeTemplateEngine();
    }

    public void prepareAndSend() throws MessagingException {
        String htmlTemplate = "emailTemplate";
        String to = "semi.honda@gmail.com";
        initializeTemplateEngine();
        context.setVariable("sender", "Thymeleaf Email");
        context.setVariable("to", to);
        context.setVariable("registrationDate", LocalDate.now().toString());

        String htmlBody = templateEngine.process(htmlTemplate, context);
        sendEmail(to, "Thymeleaf Email Demo", htmlBody);
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject); 
        helper.setText(body, true); 
        emailSender.send(message);

        LOG.info("Sending email to: {}\nSubject: {}\nBody: {}", to, subject, body);
    }

    private TemplateEngine initializeTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("templates/"); // Path to your templates folder

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        context = new Context(Locale.ENGLISH); 

        return templateEngine;
    }
}
