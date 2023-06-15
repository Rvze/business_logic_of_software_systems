package com.mpanchuk.app.service;

import com.mpanchuk.app.domain.request.AuthenticationRequest;
import com.mpanchuk.app.domain.request.RegisterRequest;
import com.mpanchuk.app.domain.response.AuthenticationResponse;
import com.mpanchuk.app.exception.UsernameExistsException;
import com.mpanchuk.app.model.Role;
import com.mpanchuk.app.model.User;
import com.mpanchuk.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws UsernameExistsException {
        var user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(Role.valueOf(request.getRole())).build();
        try {
            repository.save(user);
        } catch (Exception e) {
            throw new UsernameExistsException();
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().message("user created").token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).message("logged in").build();
    }
}
