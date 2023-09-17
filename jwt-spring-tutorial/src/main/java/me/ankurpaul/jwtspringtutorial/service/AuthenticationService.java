package me.ankurpaul.jwtspringtutorial.service;

import lombok.RequiredArgsConstructor;
import me.ankurpaul.jwtspringtutorial.controller.auth.AuthenticationRequest;
import me.ankurpaul.jwtspringtutorial.controller.auth.AuthenticationResponse;
import me.ankurpaul.jwtspringtutorial.controller.auth.RegisterRequest;
import me.ankurpaul.jwtspringtutorial.enums.Role;
import me.ankurpaul.jwtspringtutorial.model.user.User;
import me.ankurpaul.jwtspringtutorial.repository.user.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User
                .builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        if (repository.existsByEmail(user.getEmail())) {
            return AuthenticationResponse
                    .builder()
                    .token(null)
                    .build();
        } else {
            repository.save(user);
            String jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
