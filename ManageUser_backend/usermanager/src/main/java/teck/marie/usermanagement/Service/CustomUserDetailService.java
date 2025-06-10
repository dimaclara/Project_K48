package teck.marie.usermanagement.Service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teck.marie.usermanagement.Entity.Users;
import teck.marie.usermanagement.Repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    //injection of repository inService
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // find the user  by name


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with email" + email);
        }
        // return the user of Spring Security

        return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(users.getRole())));
    }
}
