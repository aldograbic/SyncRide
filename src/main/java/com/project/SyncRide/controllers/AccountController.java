package com.project.SyncRide.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.models.city.City;
import com.project.SyncRide.models.user.User;
import com.project.SyncRide.repositories.car.CarRepository;
import com.project.SyncRide.repositories.city.CityRepository;
import com.project.SyncRide.repositories.user.UserRepository;
import com.project.SyncRide.services.FileUploadService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String getAccountPage(Model model) {
        return "account";
    }

    @GetMapping("/personal-info")
    public String getPersonalInfoPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  

            User user = userRepository.findByEmail(username);

            if (user != null) {
                String maskedEmail = maskEmail(user.getEmail());
                model.addAttribute("user", user);
                model.addAttribute("maskedEmail", maskedEmail);
            }
            List<City> cities = cityRepository.getAllCities();
            model.addAttribute("cities", cities);
        }
        return "personal-info"; 
    }

@PostMapping("/personal-info")
public String updatePersonalInfo(@RequestParam("action") String action, 
                                 @RequestParam Map<String, String> formData,
                                 Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Pronađi korisnika
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user = userRepository.findByEmail(username);
    List<City> cities = cityRepository.getAllCities();
    model.addAttribute("cities", cities);

    switch (action) {
        case "updateName":
            String fullName = formData.get("fullName");
            user.setFullName(fullName);
            break;

        case "updateEmail":
            String email = formData.get("email");
            user.setEmail(email);
            userRepository.update(user);
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            return "redirect:/login"; 
        
        case "updatePhone":
            String phone = formData.get("phone");
            user.setPhone(phone);
            break;

        case "updateAddress":
            String address = formData.get("address");
            user.setAddress(address);
            break;

        case "updateCity":
            int city = Integer.parseInt(formData.get("city"));
            user.setCityId(city);
            break;

        case "updatePostCode":
            int postCode = Integer.parseInt(formData.get("postCode"));
            user.setPostCode(postCode);
            break;
    }
    userRepository.update(user);

    return "redirect:/account/personal-info";
}


    @GetMapping("/login-and-security")
    public String getLoginAndSecurityPage(Model model) {

        return "login-and-security";
    }



    @PostMapping("/login-and-security")
    public String editLoginAndSecurity(@RequestParam("password") String newPassword,
                                        @RequestParam("action") String action,
                                        RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);


        if(user == null){
            redirectAttributes.addFlashAttribute("error", "Korisnik ne postoji");
            return "redirect:/account/login-and-security";
        }


        if("updatePassword".equals(action)){
            if(passwordEncoder.matches(newPassword, user.getPassword())){
                redirectAttributes.addFlashAttribute("error", "Nova lozinka je identična kao stara");
            }else{
                String encryptedPassword = passwordEncoder.encode(newPassword);
                userRepository.updatePassword(user, encryptedPassword);
                redirectAttributes.addFlashAttribute("success", "Uspješno ste promijenili lozinku!");
                return "redirect:/account/login-and-security"; 
            }
        }

        else if("deleteUserAccount".equals(action)){
            userRepository.deleteUser(user);
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
            }
            redirectAttributes.addFlashAttribute("success", "Uspješno ste izbrisali svoj korisnički račun!");
            return "redirect:/login";
        }
        return "redirect:/account/login-and-security"; 
}

    
    @GetMapping("/vehicles")
    public String getAccountVehiclesPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  
        int userId = userRepository.findByEmail(username).getUserId();

        List<Car> cars = carRepository.findAllByUserId(userId);
        model.addAttribute("cars", cars);
        return "my-vehicles";
    }

    @GetMapping("/vehicles/new")
    public String getAddVehiclePage(Model model, @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl != null ? redirectUrl : "/account/vehicles");
        return "add-vehicle";
    }      

    @PostMapping("/vehicles/new")
    public String insertVehicle(@RequestParam("make") String make,
                                @RequestParam("model") String model,
                                @RequestParam("yearOfManufacture") int yearOfManufacture,
                                @RequestParam("color") String color,
                                @RequestParam("seatCount") int seatCount,
                                @RequestParam(value = "licensePlate", required = false) String licensePlate,
                                @RequestParam(value = "redirectUrl", defaultValue = "/account/vehicles") String redirectUrl,
                                RedirectAttributes redirectAttributes) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  
        int userId = userRepository.findByEmail(username).getUserId();
        
        Car car = new Car();
        car.setUserId(userId);
        
        try {
            car.setMake(make);
            car.setModel(model);
            car.setYearOfManufacture(yearOfManufacture);
            car.setColor(color);
            car.setSeatCount(seatCount);
            car.setLicensePlate(licensePlate);
            
            carRepository.insert(car);
            redirectAttributes.addFlashAttribute("successMessage", "Vozilo uspješno dodano!");
    
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dogodila se pogreška prilikom spremanja vozila. Molimo pokušajte ponovno.");
            return "redirect:/account/vehicles/new";
        }
    
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/account/vehicles");
    }
    

    @GetMapping("/profile")
    public String getAccountProfilePage(Model model) {

        return "profile";
    }
    

    @PostMapping("/update")
    public String updateAccount(@RequestParam("fullName") String fullName,
                                @RequestParam(value = "password", required = false) String password,
                                @RequestParam(value = "newPassword", required = false) String newPassword,
                                @RequestParam("gender") String gender,
                                @RequestParam(value = "phone", required = false) String phone,
                                @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                                @RequestParam(value = "bio", required = false) String bio,
                                RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authUser = userRepository.findByEmail(username);

        if (newPassword != null && !newPassword.isEmpty()) {
            if (password == null || !passwordEncoder.matches(password, authUser.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "Pogrešna trenutna lozinka. Pokušajte opet!");
                return "redirect:/account";
            }

            authUser.setPassword(passwordEncoder.encode(newPassword));
        }

        String profilePictureUrl = null;
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                profilePictureUrl = fileUploadService.uploadFile(profilePicture);
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("error", "Došlo je do pogreške prilikom prijenosa fotografije.");
                return "redirect:/account";
            }
        }

        authUser.setFullName(fullName);
        authUser.setGender(gender);
        authUser.setPhone(phone);

        authUser.setProfilePicture(profilePictureUrl != null ? profilePictureUrl : authUser.getProfilePicture());
        authUser.setBio(bio);

        try {
            userRepository.update(authUser);
            
            redirectAttributes.addFlashAttribute("success", "Račun uspješno ažuriran!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Došlo je do pogreške prilikom ažuriranja računa.");
            return "redirect:/account";
        }

        return "redirect:/account";
    }

    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email; 
        }
        
        String firstChar = String.valueOf(email.charAt(0));
        String lastCharBeforeAt = String.valueOf(email.charAt(email.indexOf('@') - 1));
        String middlePart = email.substring(1, email.indexOf('@') - 1).replaceAll(".", "*");
        String afterAt = email.substring(email.indexOf('@'));
        
        return firstChar + middlePart + lastCharBeforeAt + afterAt;
    }
}