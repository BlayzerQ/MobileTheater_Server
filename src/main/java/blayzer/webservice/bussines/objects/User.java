package blayzer.webservice.bussines.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String login;
    private String email;
    private String password;
    private Role role = Role.USER;
    private int money;

    public enum Role {
        ADMIN,
        DEVELOPER,
        USER,
        ANONYMOUS
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
