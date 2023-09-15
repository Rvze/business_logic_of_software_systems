package com.mpanchuk.app.delegators.order;

import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import com.mpanchuk.app.domain.response.OrderResponse;
import com.mpanchuk.app.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@RequiredArgsConstructor
public class MakeOrderDelegator implements JavaDelegate {
    @Inject
    OrderService orderService ;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username") ;
        String destination = (String) delegateExecution.getVariable("destination") ;

        System.out.println("Make Order Delegator: " + username);

        CreateOrderDto createOrderDto = CreateOrderDto.builder().username(username).destination(destination).coupon("None").build();
        OrderResponse or = orderService.makeOrder(createOrderDto) ;
        ObjectValue resJson = Variables.objectValue(or).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("order", resJson);
    }
}
