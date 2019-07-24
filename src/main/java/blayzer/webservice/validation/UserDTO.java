package blayzer.webservice.validation;

import blayzer.webservice.validation.annotations.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Login is required!")
    @Size(min = 4, message = "Login must be at least 4 characters long!")
    private String login;
    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid")
    @UniqueEmail(message = "There is already user with such email! Please, choose another one.")
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "Password must be at least 6 characters long!")
    private String password;
}
