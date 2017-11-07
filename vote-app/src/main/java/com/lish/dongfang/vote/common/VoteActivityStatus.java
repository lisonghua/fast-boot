package com.lish.dongfang.vote.common;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public enum VoteActivityStatus {
	CLOSED(0),NOT_START(1),STARTED(2),END(3);
	
	private int status;

	private VoteActivityStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public static int calculateStatus(Date startDate,Date endDate) {
		Date now = new Date();
		if(now.compareTo(startDate)<0) {
			return NOT_START.getStatus();
		}
		if(now.compareTo(DateUtils.addDays(endDate, 1))>0) {
			return END.getStatus();
		}
		return STARTED.getStatus();
	}
}
