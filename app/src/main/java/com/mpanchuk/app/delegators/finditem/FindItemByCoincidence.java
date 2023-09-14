package com.mpanchuk.app.delegators.finditem;

import com.mpanchuk.app.delegators.utils.CheckPageVariables;
import com.mpanchuk.app.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.mpanchuk.app.delegators.utils.ErrorMessages.REGEXP_NOT_FOUND;
import static com.mpanchuk.app.delegators.utils.Variables.ITEMS;
import static com.mpanchuk.app.delegators.utils.Variables.REGEXP;

@Component
@RequiredArgsConstructor
public class FindItemByCoincidence implements JavaDelegate {
    private final ItemService itemService;
    private final CheckPageVariables pageVariables;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var pagePair = pageVariables.check(delegateExecution);
        String regexp;
        if (delegateExecution.hasVariable(REGEXP)) {
            regexp = (String) delegateExecution.getVariable(REGEXP);
        } else {
            throw new BpmnError(REGEXP_NOT_FOUND);
        }
        var items = itemService.getItemByRegexp(regexp, pagePair.getFirst(), pagePair.getSecond());
        delegateExecution.setVariable(ITEMS, items);
    }
}
