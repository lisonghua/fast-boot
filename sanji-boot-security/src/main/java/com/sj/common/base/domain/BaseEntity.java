package com.sj.common.base.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by yangrd on 2017/7/1.
 */
@MappedSuperclass
public class BaseEntity<T> extends AbstractPersistable<Long> {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    private String remark;

    public Date getCreatedTime() {
        return createdTime;
    }

    public T setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return (T) this;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public T setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
        return (T) this;
    }

    public String getRemark() {
        return remark;
    }

    public T setRemark(String remark) {
        this.remark = remark;
        return (T) this;
    }


}
