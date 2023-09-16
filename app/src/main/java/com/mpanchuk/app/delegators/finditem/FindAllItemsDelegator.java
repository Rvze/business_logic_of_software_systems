package com.mpanchuk.app.delegators.finditem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpanchuk.app.delegators.utils.CheckPageVariables;
import com.mpanchuk.app.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import static com.mpanchuk.app.delegators.utils.Variables.ITEMS;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllItemsDelegator implements JavaDelegate {
    private final ItemService itemService;
    private final CheckPageVariables pageVariables;
    private final ObjectMapper mapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        var pair = pageVariables.check(delegateExecution);
        var items = itemService.getAllItems(pair.getFirst(), pair.getSecond());
        try {
            String jsonItems = mapper.writeValueAsString(items);
            ObjectValue objectValue = Variables.objectValue(jsonItems).serializationDataFormat("application/json").create();
            delegateExecution.setVariable(ITEMS, objectValue);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
