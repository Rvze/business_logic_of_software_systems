package com.mpanchuk.app.delegators.finditem;

import com.mpanchuk.app.delegators.utils.CheckPageVariables;
import com.mpanchuk.app.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.mpanchuk.app.delegators.utils.Variables.ITEMS;

@Component
@RequiredArgsConstructor
public class FindAllItemsDelegator implements JavaDelegate {
    private final ItemService itemService;
    private final CheckPageVariables pageVariables;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var pair = pageVariables.check(delegateExecution);
        var items = itemService.getAllItems(pair.getFirst(), pair.getSecond());
        delegateExecution.setVariable(ITEMS, items);
    }
}
