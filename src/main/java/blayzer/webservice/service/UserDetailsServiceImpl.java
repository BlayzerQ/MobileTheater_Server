package blayzer.webservice.service;

import blayzer.webservice.dal.dao.entity.UserEntity;
import blayzer.webservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(name);
        if (userEntity != null) {
            return userEntity;
        }
        throw new UsernameNotFoundException("User `" + name + "` not found");
    }
}
