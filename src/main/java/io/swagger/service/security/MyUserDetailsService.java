package io.swagger.service.security;

import io.swagger.model.security.MyUserDetails;
import io.swagger.repo.UserProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserProfileRepository repository;

    public MyUserDetailsService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByEmail(s)
                .map(userProfileEntity -> new MyUserDetails(userProfileEntity.getUserCredential(), userProfileEntity.getRole()))
                .orElseThrow(()->new UsernameNotFoundException("Username not found."));
    }
}
