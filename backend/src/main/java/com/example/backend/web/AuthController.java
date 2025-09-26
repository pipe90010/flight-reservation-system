package com.example.backend.web;

import com.example.backend.domain.Role;
import com.example.backend.domain.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JwtService;
import com.example.backend.security.UserDetailsServiceImpl;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

record LoginRequest(String username, String password) {}
record RegisterRequest(String username, String password) {}
record TokenResponse(String token) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwt;
    private final UserDetailsServiceImpl uds;
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;

    public AuthController(JwtService jwt, UserDetailsServiceImpl uds, PasswordEncoder encoder, UserRepository userRepo) {
        this.jwt = jwt; 
        this.uds = uds; 
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    // ✅ Registro
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // usuario ya existe
        }

        User newUser = new User(null, req.username(), encoder.encode(req.password()), Role.USER);
        userRepo.save(newUser);

        var userDetails = uds.loadUserByUsername(req.username());
        String token = jwt.generate(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {
        var user = uds.loadUserByUsername(req.username());
        if (!encoder.matches(req.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new TokenResponse(jwt.generate(user)));
    }
}
