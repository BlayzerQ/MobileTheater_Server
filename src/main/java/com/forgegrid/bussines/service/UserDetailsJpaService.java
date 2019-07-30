package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.dal.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsJpaService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getUserEntityByLogin(name);
        if (userEntity != null) {
            return userEntity;
        }
        throw new UsernameNotFoundException("User `" + name + "` not found");
    }
}
