package com.exadel.discount.platform.service;

import com.exadel.discount.platform.domain.User;
import com.exadel.discount.platform.repository.UserRepository;
import com.exadel.discount.platform.service.dto.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findUserByEmail(s);
        if (user == null){
            throw new UsernameNotFoundException(s);
        }
        return new MyUserDetails(user);
    }
}
