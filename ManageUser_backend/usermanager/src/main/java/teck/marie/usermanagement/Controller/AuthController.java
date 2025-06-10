package teck.marie.usermanagement.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teck.marie.usermanagement.Entity.Users;
import teck.marie.usermanagement.Repository.UserRepository;
import teck.marie.usermanagement.security.JwtUtils;

import java.util.HashMap;
import java.util.Map;
@Slf4j

@Tag(name = "User Management", description = "API that allows you to manage users and their authentification")
@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final  AuthenticationManager authenticationManager;

    @Operation(summary = "Register a person with their email")
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody Users users) {
        if(userRepository.findByEmail(users.getEmail()) != null){
            return  ResponseEntity.badRequest().body("Email Already Exist");

        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return ResponseEntity.ok(userRepository.save(users));
    }

    // implementation of loging

    @Operation(summary = "authenticate a user by password and email")
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));
            if (authentication.isAuthenticated()) {
                Map<String, Object> authData= new HashMap<>();
                authData.put("token",jwtUtils.generateToken(users.getEmail()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }catch (AuthenticationException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
