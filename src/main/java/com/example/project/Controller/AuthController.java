package com.example.project.Controller;

import com.example.project.Model.User;
import com.example.project.Security.JwtService;
import com.example.project.Service.UserService.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authManager,
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "Error: username already exists.";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("ROLE_USER");
        newUser.setEnabled(true);

        userRepository.save(newUser);
        return "User registered successfully!";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // This triggers authentication via UserDetailsService and PasswordEncoder
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // After successful authentication, generate the JWT
        UserDetails user = (UserDetails) auth.getPrincipal();
        return jwtService.generateToken(user);
    }
}
