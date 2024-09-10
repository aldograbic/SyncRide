package com.project.SyncRide.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Potvrdite svoju registraciju | SyncRide");
        message.setText("Kliknite na sljedeću poveznicu kako biste potvrdili svoju registraciju: " + link);
        mailSender.send(message);
    }
}