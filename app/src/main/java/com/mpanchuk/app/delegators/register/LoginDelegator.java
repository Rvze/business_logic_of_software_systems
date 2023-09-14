package com.mpanchuk.app.delegators.register;

import com.mpanchuk.app.repository.UserRepository;
import com.mpanchuk.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LoginDelegator implements JavaDelegate {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username") ;
        String password = (String) delegateExecution.getVariable("password") ;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new BpmnError("auth_error") ;
        }
        var user = repository.findByUsername(username).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        delegateExecution.setVariable("token", jwtToken);
    }
}
