package com.project.SyncRide.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class DatabaseLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String referrerUrl = request.getHeader("Referer");
        String fallbackUrl = "/login?error";
        String redirectUrl = referrerUrl != null ? referrerUrl + "?error" : fallbackUrl;

        if (exception instanceof BadCredentialsException) {
            response.sendRedirect(redirectUrl);
        } else if (exception instanceof EmailNotVerifiedException) {
            response.sendRedirect(referrerUrl + "?notVerified");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            response.sendRedirect(referrerUrl + "?notVerified");
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}