package blayzer.webservice.controllers;

import blayzer.webservice.dal.dao.entity.UserEntity;
import blayzer.webservice.presentation.dto.EditProfileForm;
import blayzer.webservice.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
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
        return "/accountedit";
    }

    @PostMapping("/account/edit")
    public String accountedit(@Valid @ModelAttribute("user") EditProfileForm editProfileForm, Errors errors, Authentication authentication) {
        if (errors.hasErrors()) {
            return "accountedit";
        }
        UserEntity user = (UserEntity) authentication.getPrincipal();
        user.alterLogin(editProfileForm.getLogin());
        user.alterEmail(editProfileForm.getEmail());
        user.alterPassword(editProfileForm.getPassword());
        userService.persist(user);
        return "redirect:/account";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
