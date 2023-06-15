package com.mpanchuk.app.config;

import com.mpanchuk.app.domain.response.ItemToAddResponse;
import com.mpanchuk.app.service.ItemService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApproveItemsJob implements Job {

    @Autowired
    private ItemService itemService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Page<ItemToAddResponse> itms = itemService.getItemsToAdd(0, 100);
        List<Long> itmsToAddIds = new ArrayList<>();
        for (ItemToAddResponse resp : itms) {
            itmsToAddIds.add(resp.getId());
        }
        itemService.addItemsFromManager(itmsToAddIds);
    }
}
