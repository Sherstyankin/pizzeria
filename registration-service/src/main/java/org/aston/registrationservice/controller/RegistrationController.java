package org.aston.registrationservice.controller;

import jakarta.validation.Valid;
import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.exceptions.AppError;
import org.aston.registrationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@SuppressWarnings({"unused"})
public class RegistrationController {
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")

    public ModelAndView registrationClient(@Valid UserDto userDto,
                                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userDto",userDto);

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return  new ModelAndView("registration");
        }

       userService.registerUser(userDto);
        return new ModelAndView("login");
    }
}
