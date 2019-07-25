package blayzer.webservice.presentation.dto;

import blayzer.webservice.validation.annotations.UniqueEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class EditProfileForm {
    @Size(min = 4, max = 16, message = "Login must be between 4 and 16 characters long!")
    private String login;
    @Email(message = "Email should be valid!")
    @UniqueEmail(message = "There is already user with such email! Please, choose another one.")
    private String email;
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long!")
    private String password;
}