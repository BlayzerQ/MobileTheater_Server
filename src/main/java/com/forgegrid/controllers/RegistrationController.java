package com.forgegrid.controllers;

import com.forgegrid.bussines.service.UserService;
import com.forgegrid.presentation.dto.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView model) {
        model.addObject("user", new RegistrationForm());
        return model;
    }

    @PostMapping("/registration")
    public String processRegistration(@Valid @ModelAttribute("user") RegistrationForm registrationForm, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "registration";
        }
        try {
            userService.registerAndLogin(registrationForm, request);
        } catch (ServletException e) {
            e.printStackTrace();
            return "registration";
        }
        return "index";
    }
}
