package com.mpanchuk.app.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {
    private static final String CRON_EVERY_24_HOURS = "0 0 0 1/1 * ? *";

    @Bean(name = "approveItems")
    public JobDetailFactoryBean jobMemberClassStats() {
        return QuartzConfig.createJobDetail(ApproveItemsJob.class, "Class Statistics Job");
    }

    @Bean(name = "approveItemsTrigger")
    public CronTriggerFactoryBean triggerMemberClassStats(@Qualifier("approveItems") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_24_HOURS, "Class Statistics Trigger");
    }
}
