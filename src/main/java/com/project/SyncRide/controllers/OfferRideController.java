package com.project.SyncRide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferRideController {
    
    @GetMapping("/offer")
    public String getOfferRidePage() {
        return "offer-ride";
    }
    
}