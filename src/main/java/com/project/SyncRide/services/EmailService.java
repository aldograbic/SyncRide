package com.project.SyncRide.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUsername; 

    public void sendConfirmationEmail(String to, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        
        String link = "http://localhost:8080/confirm"; // prilagodi ovaj link prema svojoj aplikaciji

        String htmlContent = "<h3>Potvrdite svoju registraciju | SyncRide</h3>"
                + "<p>Kliknite na sljedeću poveznicu kako biste potvrdili svoju registraciju:</p>"
                + "<form action='" + link + "' method='POST'>"
                + "<input type='hidden' name='token' value='" + token + "'/>"
                + "<button type='submit'>Potvrdi registraciju</button>"
                + "</form>";
        
        helper.setText(htmlContent, true); // true za HTML sadržaj
        helper.setFrom(mailUsername);
        helper.setTo(to);
        helper.setSubject("Potvrdite svoju registraciju | SyncRide");

        mailSender.send(mimeMessage);
    }
}
