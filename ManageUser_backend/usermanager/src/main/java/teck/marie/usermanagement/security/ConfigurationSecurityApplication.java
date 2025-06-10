package teck.marie.usermanagement.security;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import teck.marie.usermanagement.Service.CustomUserDetailService;
import teck.marie.usermanagement.filter.JwtFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurityApplication {

    private final CustomUserDetailService customUserDetailService;
    private final JwtUtils jwtUtils;

    /*public ConfigurationSecurityApplication(CustomUserDetailService customUserDetailService,JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailService = customUserDetailService;


    }*/
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
         return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**","/api/auth/login","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html").permitAll()
                                .anyRequest().authenticated())

                .addFilterBefore(new JwtFilter(customUserDetailService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
