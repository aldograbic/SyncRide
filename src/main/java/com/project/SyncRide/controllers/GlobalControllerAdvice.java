package com.project.SyncRide.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.SyncRide.config.EmailNotVerifiedException;
import com.project.SyncRide.models.user.User;
import com.project.SyncRide.repositories.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserRepository userRepository;

    public GlobalControllerAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            
            User user = userRepository.findByEmail(username);

            if (user != null) {
                model.addAttribute("user", user);
            }
        }

        String currentUri = request.getRequestURI();
        model.addAttribute("currentUri", currentUri);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public String handleEmailNotVerifiedException(Model model) {
        model.addAttribute("error", "Prije prijave potvrdite svoju email adresu.");
        return "login";
    }

}