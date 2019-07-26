package blayzer.webservice.bussines.service;

import blayzer.webservice.dal.entity.UserEntity;
import blayzer.webservice.presentation.dto.EditProfileForm;
import blayzer.webservice.presentation.dto.RegistrationForm;
import blayzer.webservice.dal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerAndLogin(RegistrationForm registrationForm, HttpServletRequest request) throws ServletException {
        String login = registrationForm.getLogin();
        String password = registrationForm.getPassword();
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setEmail(registrationForm.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.saveAndFlush(user);
        request.login(login, password);
    }

    public void updateWithFormAndPersist(EditProfileForm editProfileForm, UserEntity user) {
        user.alterLogin(editProfileForm.getLogin());
        user.alterEmail(editProfileForm.getEmail());
        user.alterPassword(editProfileForm.getPassword());
        userRepository.saveAndFlush(user);
    }

    public boolean isEmailRegistered(@Nullable String email) {
        return email != null && userRepository.existsUserEntityByEmail(email);
    }
}
