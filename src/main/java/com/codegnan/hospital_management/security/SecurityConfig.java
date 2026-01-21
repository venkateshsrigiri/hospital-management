package com.codegnan.hospital_management.security;

import com.codegnan.hospital_management.security.AuthSuccessHandler;
import com.codegnan.hospital_management.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationProvider authenticationProvider;
    private final AuthSuccessHandler successHandler;

    public SecurityConfig(CustomAuthenticationProvider authenticationProvider,
                          AuthSuccessHandler successHandler) {
        this.authenticationProvider = authenticationProvider;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // 🔴 VERY IMPORTANT
            .httpBasic(httpBasic -> httpBasic.disable())

            // CSRF disabled for simplicity (OK for interviews / demos)
            .csrf(csrf -> csrf.disable())

            .authenticationProvider(authenticationProvider)

            .authorizeHttpRequests(auth -> auth
                // Public pages
                .requestMatchers(
                        "/",
                        "/index.html",
                        "/login.html",
                        "/signup.html",
                        "/admin-login.html",
                        "/css/**",
                        "/js/**",
                        "/style.css",
                        "/api/auth/**"
                ).permitAll()

                // Admin-only
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/admin.html").hasRole("ADMIN")

                // User-only
                .requestMatchers("/api/appointments/**").hasRole("USER")
                .requestMatchers("/user.html").hasRole("USER")

                // Everything else requires login
                .anyRequest().authenticated()
            )

            // ✅ FORM LOGIN (this removes browser popup)
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureUrl("/login.html?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
