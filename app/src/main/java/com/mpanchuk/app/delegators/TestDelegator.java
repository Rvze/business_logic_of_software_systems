package com.mpanchuk.app.delegators;

import com.mpanchuk.app.model.Role;
import com.mpanchuk.app.model.User;
import com.mpanchuk.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDelegator implements JavaDelegate {
    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        userRepository.save(User.builder().password("A").username("B").role(Role.ADMIN).build());
    }
}
