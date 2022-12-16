package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.entity.User;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname)
            throws UsernameNotFoundException {

        User user = userRepository.findUserByNickname(nickname)
                .orElseThrow(()->
                        new UsernameNotFoundException("No se encontro el usuario -> username or email : " + nickname)
                );

        return  UserPrincipal.build(user);
    }
}
