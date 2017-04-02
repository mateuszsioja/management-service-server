package com.msjs.managementservice.security;

import com.msjs.managementservice.model.Role;
import com.msjs.managementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by jakub on 01.04.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.msjs.managementservice.model.User user = userRepository.findUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.msjs.managementservice.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), true, true, user.isCredentialsNotExpired(), true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }
}
