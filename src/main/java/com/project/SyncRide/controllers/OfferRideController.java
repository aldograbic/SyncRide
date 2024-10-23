package com.project.SyncRide.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.repositories.car.CarRepository;
import com.project.SyncRide.repositories.user.UserRepository;

@Controller
public class OfferRideController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;
    
    @GetMapping("/offer")
    public String getOfferRidePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  
        int userId = userRepository.findByEmail(username).getUserId();

        List<Car> cars = carRepository.findAllByUserId(userId);
        model.addAttribute("cars", cars);

        return "offer-ride";
    }

    @PostMapping("/offer")
    public String offerRide(@RequestParam("carId") int carId,
                            @RequestParam("startLocation") String startLocation,
                            @RequestParam("startLat") double startLat,
                            @RequestParam("startLng") double startLng,
                            @RequestParam("endLocation") String endLocation,
                            @RequestParam("endLat") double endLat,
                            @RequestParam("endLng") double endLng,
                            @RequestParam("departureTime") LocalDateTime departureTime,
                            @RequestParam("availableSeats") int availableSeats,
                            @RequestParam("pricePerPerson") double pricePerPerson,
                            @RequestParam(value = "additionalInfo", required = false) String additionalInfo,
                            RedirectAttributes redirectAttributes) {
        // Save ride details including locations and coordinates
        return "redirect:/";
    }    
}