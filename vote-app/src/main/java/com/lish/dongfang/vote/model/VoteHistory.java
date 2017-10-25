package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * 投票历史信息
 * 
 */
@Entity
@Table(name="vote_history")
@NamedQuery(name="VoteHistory.findAll", query="SELECT v FROM VoteHistory v")
public class VoteHistory extends com.sj.common.base.domain.BaseEntity<VoteHistory> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="action_type")
	private byte actionType;

	@Column(name="activity_id")
	private BigInteger activityId;

	@Column(name="candidate_id")
	private BigInteger candidateId;

	private String host;

	private String ip;

	private String port;

	private byte status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vote_time")
	private Date voteTime;

	@Column(name="voter_id")
	private BigInteger voterId;

	public VoteHistory() {
	}

	public byte getActionType() {
		return this.actionType;
	}

	public void setActionType(byte actionType) {
		this.actionType = actionType;
	}

	public BigInteger getActivityId() {
		return this.activityId;
	}

	public void setActivityId(BigInteger activityId) {
		this.activityId = activityId;
	}

	public BigInteger getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(BigInteger candidateId) {
		this.candidateId = candidateId;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getVoteTime() {
		return this.voteTime;
	}

	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}

	public BigInteger getVoterId() {
		return this.voterId;
	}

	public void setVoterId(BigInteger voterId) {
		this.voterId = voterId;
	}

}