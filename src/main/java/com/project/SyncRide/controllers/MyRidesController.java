package com.project.SyncRide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-rides")
public class MyRidesController {
    
    @GetMapping
    public String getMyRidesPage() {
        return "my-rides";
    }
}
