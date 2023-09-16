package com.mpanchuk.app.delegators.finditem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpanchuk.app.delegators.utils.CheckPageVariables;
import com.mpanchuk.app.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import static com.mpanchuk.app.delegators.utils.ErrorMessages.REGEXP_NOT_FOUND;
import static com.mpanchuk.app.delegators.utils.Variables.ITEMS;
import static com.mpanchuk.app.delegators.utils.Variables.REGEXP;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindItemByCoincidence implements JavaDelegate {
    private final ItemService itemService;
    private final CheckPageVariables pageVariables;
    private final ObjectMapper mapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        var pagePair = pageVariables.check(delegateExecution);
        String regexp;
        if (delegateExecution.hasVariable(REGEXP)) {
            regexp = (String) delegateExecution.getVariable(REGEXP);
        } else {
            throw new BpmnError(REGEXP_NOT_FOUND);
        }
        var items = itemService.getItemByRegexp(regexp, pagePair.getFirst(), pagePair.getSecond());
        try {
            String json = mapper.writeValueAsString(items);
            ObjectValue value = Variables.objectValue(json).serializationDataFormat("application/json").create();
            delegateExecution.setVariable(ITEMS, value);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
