package com.sj.module.sys.event;

import com.sj.module.sys.domain.SiteLogger;
import org.springframework.context.ApplicationEvent;

/**
 * Created by yangrd on 2017/4/18.
 */
public class SiteLoggerEvent extends ApplicationEvent {

    public SiteLoggerEvent(SiteLogger source) {
        super(source);
    }

    public SiteLogger getSiteLogger() {
        return (SiteLogger) getSource();
    }
}
