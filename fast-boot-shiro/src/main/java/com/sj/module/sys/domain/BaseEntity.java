package com.sj.module.sys.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunxyz on 2017/3/14.
 */
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> extends AbstractPersistable<PK> {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    private String remark;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
