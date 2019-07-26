package blayzer.webservice.controllers;

import blayzer.webservice.bussines.service.UserService;
import blayzer.webservice.dal.entity.UserEntity;
import blayzer.webservice.presentation.dto.EditProfileForm;
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
public class EditProfileController {

    private final UserService userService;

    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account/edit")
    public String accountedit(Model model) {
        model.addAttribute("user", new EditProfileForm());
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
