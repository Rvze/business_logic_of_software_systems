package com.mpanchuk.app.delegators.register;

import com.mpanchuk.app.exception.UsernameExistsException;
import com.mpanchuk.app.model.Role;
import com.mpanchuk.app.model.User;
import com.mpanchuk.app.repository.UserRepository;
import com.mpanchuk.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisterDelegator implements JavaDelegate {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username") ;
        String password = (String) delegateExecution.getVariable("password") ;
        String role = (String) delegateExecution.getVariable("role") ;

        try {
            validate(username, role);
        } catch (Exception e) {
            throw new BpmnError("invalid_input");
        }

        var user = User.builder().username(username).password(passwordEncoder.encode(password)).role(Role.valueOf(role)).build();
        repository.save(user) ;
        var jwtToken = jwtService.generateToken(user) ;
        delegateExecution.setVariable("token", jwtToken);
    }

    private void  validate(String username, String role) throws Exception {
        Optional<User> u = repository.findByUsername(username);
        if (u.isPresent()) throw new UsernameExistsException();
        if (!(role.equals("BUYER") || role.equals("SUPPLIER") || role.equals("ADMIN"))) throw new UsernameExistsException();
    }
}
