package com.mpanchuk.app.service;

import com.mpanchuk.app.exception.UsernameExistsException;
import com.mpanchuk.app.model.Role;
import com.mpanchuk.app.model.User;
import com.mpanchuk.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterProcess implements JavaDelegate {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("ABAS");

        String username = (String) delegateExecution.getVariable("username") ;
        String password = (String) delegateExecution.getVariable("password") ;
        String role = (String) delegateExecution.getVariable("role") ;

        var user = User.builder().username(username).password(passwordEncoder.encode(password)).role(Role.valueOf(role)).build();

        repository.save(user);
        System.out.println("CHLEN");

        var jwtToken = jwtService.generateToken(user) ;
        delegateExecution.setVariable("token", jwtToken);
    }
}
