package com.forgegrid.controllers;

import com.forgegrid.bussines.service.UserService;
import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.presentation.dto.EditProfileForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EditProfileController {

    private final UserService userService;

    @GetMapping("/account/edit")
    public String accountedit(Model model, @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("user", new EditProfileForm());
        model.addAttribute("currentLogin", user.getLogin());
        model.addAttribute("currentEmail", user.getEmail());
        return "accountedit";
    }

    @PostMapping("/account/edit")
    public String accountedit(@Valid @ModelAttribute("user") EditProfileForm editProfileForm, Errors errors, @AuthenticationPrincipal UserEntity user) {
        if (errors.hasErrors()) {
            return "accountedit";
        }
        userService.updateWithFormAndPersist(editProfileForm, user);
        return "redirect:/account";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
