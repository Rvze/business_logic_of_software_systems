package com.mpanchuk.app.delegators.stash;

import com.mpanchuk.app.domain.response.StashResponse;
import com.mpanchuk.app.mapper.CityMapper;
import com.mpanchuk.app.mapper.ItemMapper;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.model.Stash;
import com.mpanchuk.app.repository.ItemRepository;
import com.mpanchuk.app.repository.StashRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddItemDelegator implements JavaDelegate {

    private final ItemRepository itemRepository;
    private final StashRepository stashRepository;
    private final ItemMapper itemMapper;
    private final CityMapper cityMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long itemId = (Long) delegateExecution.getVariable("item_id");
        String username = (String) delegateExecution.getVariable("username");
        String action = (String) delegateExecution.getVariable("action");
        Long amount = (Long) delegateExecution.getVariable("amount");

        Stash stash;

        if (action.equals("add")) {
            try {
                stash = addItem(username, itemId, amount);
            } catch (Exception e) {
                throw new BpmnError("no_item");
            }
        } else if (action.equals("delete")) {
            try {
                stash = deleteItem(username, itemId, amount);
            } catch (Exception e) {
                throw new BpmnError("no_item");
            }
        } else {
            throw new BpmnError("no_action");
        }

        ObjectValue resJson = Variables.objectValue(mapper(stash)).serializationDataFormat("application/json").create();

        delegateExecution.setVariable("stash", resJson);
    }

    private Stash addItem(String username, Long itemId, Long amount) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return stashRepository.addItem(username, item, amount.intValue());
    }

    private Stash deleteItem(String username, Long itemId, Long amount) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return stashRepository.deleteItem(username, item, amount.intValue());
    }

    private List<StashResponse> mapper(Stash stash) {
        List<StashResponse> stashResponses = new ArrayList<>();
        if (stash == null)
            return List.of();
        stash.getItems().forEach(it ->
                stashResponses.add(StashResponse.builder()
                        .itemResponse(itemMapper.toResponse(it))
                        .cities(it.getCities().stream().map(cityMapper::toResponse).collect(Collectors.toList()))
                        .amount(stash.getItems().size())
                        .build()));
        return stashResponses;
    }
}
