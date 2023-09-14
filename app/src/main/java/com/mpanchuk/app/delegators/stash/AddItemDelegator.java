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
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long itemId = (Long) delegateExecution.getVariable("item_id");
        String jwt = (String) delegateExecution.getVariable("jwt_token");
        Long amount = (Long) delegateExecution.getVariable("amount") ;
        Item item ;

        try {
            item = itemRepository.findById(itemId).orElseThrow();
        } catch (Exception e) {
            throw new BpmnError("no_item") ;
        }

        var stash = stashRepository.addItem(jwt, item, amount.intValue()) ;

        delegateExecution.setVariable("stash", mapper(stash));
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
