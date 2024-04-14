package org.aston.registrationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.aston.registrationservice.dto.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@SuppressWarnings({"unused"})
public class LoginController {



    @Secured({"ADMIN", "USER"})
    @GetMapping("/orderpage")
    public String goToMainPage(Authentication authentication, Model model) {

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String getUsernameFromSecurity = userDetails.getUsername();
            model.addAttribute("username", getUsernameFromSecurity);
        }
        return "orderpage";
    }

    @GetMapping("/login")
    public String addClient() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


//    @PostMapping("/registration")
//    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
//        return authService.createNewUser(registrationUserDto);
//    }
}
