package com.sj.module.sys.publisher;

import com.sj.module.sys.domain.SiteLogger;
import com.sj.module.sys.event.SiteLoggerEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by yangrd on 2017/4/18.
 */
@Component
public class SiteLoggerEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(SiteLogger siteLogger) {
        publisher.publishEvent(new SiteLoggerEvent(siteLogger));
    }
}
