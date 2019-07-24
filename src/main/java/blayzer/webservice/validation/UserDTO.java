package blayzer.webservice.validation;

import blayzer.webservice.entity.User;
import blayzer.webservice.entity.enums.UserRoleEnum;
import blayzer.webservice.validation.annotations.UniqueEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull
    @NotBlank(message = "Login is required!")
    @Size(min = 4, message = "Login must be at least 4 characters long!")
    private String login;
    @NotNull
    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid")
    @UniqueEmail(message = "There is already user with such email! Please, choose another one.")
    private String email;
    @NotNull
    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "Password must be at least 6 characters long!")
    private String password;
    private UserRoleEnum role = UserRoleEnum.USER;
    private int money = 0;

    public UserDTO(@NotNull User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.money = user.getMoney();
        this.role = user.getRole();
    }
}
