package com.project.SyncRide.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.models.ride.Ride;
import com.project.SyncRide.repositories.car.CarRepository;
import com.project.SyncRide.repositories.ride.RideRepository;
import com.project.SyncRide.repositories.user.UserRepository;

@Controller
public class OfferRideController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RideRepository rideRepository;

    @Value("${google.maps.api-key}")
    private String googleMapsApiKey;
    
    @GetMapping("/offer")
    public String getOfferRidePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  
        int userId = userRepository.findByEmail(username).getUserId();

        List<Car> cars = carRepository.findAllByUserId(userId);
        model.addAttribute("cars", cars);
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);

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
                            @RequestParam("departureTime") String departureTime,
                            @RequestParam("availableSeats") int availableSeats,
                            @RequestParam("pricePerPerson") BigDecimal pricePerPerson,
                            @RequestParam(value = "additionalInfo", required = false) String additionalInfo,
                            RedirectAttributes redirectAttributes) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int driverId = userRepository.findByEmail(username).getUserId();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDepartureTime = LocalDateTime.parse(departureTime, formatter);

        Ride ride = new Ride();
        ride.setDriverId(driverId);
        ride.setCarId(carId);
        ride.setStartLocation(startLocation);
        ride.setEndLocation(endLocation);
        ride.setDepartureTime(parsedDepartureTime);
        ride.setStartLatitude(startLat);
        ride.setStartLongitude(startLng);
        ride.setEndLatitude(endLat);
        ride.setEndLongitude(endLng);
        ride.setAvailableSeats(availableSeats);
        ride.setPricePerPerson(pricePerPerson);
        ride.setAdditionalInfo(additionalInfo);

        rideRepository.insertRide(ride);

        redirectAttributes.addFlashAttribute("successMessage", "Ponuda vožnje uspješno kreirana!");
        return "redirect:/";
    }    
}