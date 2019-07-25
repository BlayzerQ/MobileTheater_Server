package blayzer.webservice.presentation.dto;

import blayzer.webservice.validation.annotations.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationForm {
    @NotBlank(message = "Login is required!")
    @Size(min = 4, max = 16, message = "Login must be between 4 and 16 characters long!")
    private String login;
    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    @UniqueEmail(message = "There is already user with such email! Please, choose another one.")
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long!")
    private String password;
}
