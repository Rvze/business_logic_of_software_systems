package com.mpanchuk.app.delegators.utils;

import com.mpanchuk.app.util.model.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class CheckPageVariables {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 0;

    public Pair<Integer, Integer> check(DelegateExecution delegateExecution) {
        long pageNum = delegateExecution.hasVariable(Variables.PAGE_NUM) ? (Long) delegateExecution.getVariable(Variables.PAGE_NUM) : PAGE_NUM;
        long pageSize = delegateExecution.hasVariable(Variables.PAGE_SIZE) ? (Long) delegateExecution.getVariable(Variables.PAGE_SIZE) : PAGE_SIZE;
        return new Pair<>((int) pageNum, (int) pageSize);
    }
}
