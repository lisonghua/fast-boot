package com.lish.dongfang.cloud.monitor.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;


/**
 * 为监控的服务添加邮件通知
 * @author lisong
 *
 */
@Configuration
@ConditionalOnProperty("fast.boot.monitor.notifier")
@EnableScheduling
public class NotifierConfiguration {
	
    @Autowired
    private Notifier notifier;

    //服务上线或者下线都通知
    private String[] reminderStatuses = { "DOWN" };
    
    @Bean
    @Primary
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier);
        //设定时间，5分钟提醒一次
//        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(5)); 
        //设定监控服务状态，状态改变为给定值的时候提醒
        remindingNotifier.setReminderStatuses(reminderStatuses);
        return remindingNotifier;
    }

//    @Scheduled(fixedRate = 60_000L) 
//    public void remind() {
//        remindingNotifier().sendReminders();
//    }
}