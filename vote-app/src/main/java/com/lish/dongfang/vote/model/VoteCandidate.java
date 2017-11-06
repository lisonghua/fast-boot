package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 候选人信息
 * 
 */
@Entity
@Table(name="vote_candidate")
@NamedQuery(name="VoteCandidate.findAll", query="SELECT v FROM VoteCandidate v")
public class VoteCandidate extends FastBaseEntity<VoteCandidate> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(length=500)
	private String cover;

	@Lob
	private String description;
	
	@Column(length=20)
	private String name;

	/**
	 * 编号
	 */
	private String number;

	private byte status;

	@Column(name="vote_count")
	private int voteCount;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	//bi-directional many-to-one association to VoteCandidateDetail
	@OneToMany(mappedBy="voteCandidate")
	private List<VoteCandidateDetail> voteCandidateDetails;

	public VoteCandidate() {
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

	public List<VoteCandidateDetail> getVoteCandidateDetails() {
		return this.voteCandidateDetails;
	}

	public void setVoteCandidateDetails(List<VoteCandidateDetail> voteCandidateDetails) {
		this.voteCandidateDetails = voteCandidateDetails;
	}

	public VoteCandidateDetail addVoteCandidateDetail(VoteCandidateDetail voteCandidateDetail) {
		getVoteCandidateDetails().add(voteCandidateDetail);
		voteCandidateDetail.setVoteCandidate(this);

		return voteCandidateDetail;
	}

	public VoteCandidateDetail removeVoteCandidateDetail(VoteCandidateDetail voteCandidateDetail) {
		getVoteCandidateDetails().remove(voteCandidateDetail);
		voteCandidateDetail.setVoteCandidate(null);

		return voteCandidateDetail;
	}

}