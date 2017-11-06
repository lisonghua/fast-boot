package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 候选人详细信息
 * 
 */
@Entity
@Table(name="vote_candidate_detail")
@NamedQuery(name="VoteCandidateDetail.findAll", query="SELECT v FROM VoteCandidateDetail v")
public class VoteCandidateDetail extends FastBaseEntity<VoteCandidateDetail> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(length=200)
	private String address;
	
	@Column(length=500)
	private String description;
	
	@Column(length=20)
	private String tel;

	//bi-directional many-to-one association to VoteCandidateCustomize
	@OneToMany(mappedBy="voteCandidateDetail")
	private List<VoteCandidateCustomize> voteCandidateCustomizes;

	//bi-directional many-to-one association to VoteCandidate
	@ManyToOne
	@JoinColumn(name="vote_candidate_id")
	private VoteCandidate voteCandidate;

	public VoteCandidateDetail() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<VoteCandidateCustomize> getVoteCandidateCustomizes() {
		return this.voteCandidateCustomizes;
	}

	public void setVoteCandidateCustomizes(List<VoteCandidateCustomize> voteCandidateCustomizes) {
		this.voteCandidateCustomizes = voteCandidateCustomizes;
	}

	public VoteCandidateCustomize addVoteCandidateCustomize(VoteCandidateCustomize voteCandidateCustomize) {
		getVoteCandidateCustomizes().add(voteCandidateCustomize);
		voteCandidateCustomize.setVoteCandidateDetail(this);

		return voteCandidateCustomize;
	}

	public VoteCandidateCustomize removeVoteCandidateCustomize(VoteCandidateCustomize voteCandidateCustomize) {
		getVoteCandidateCustomizes().remove(voteCandidateCustomize);
		voteCandidateCustomize.setVoteCandidateDetail(null);

		return voteCandidateCustomize;
	}

	public VoteCandidate getVoteCandidate() {
		return this.voteCandidate;
	}

	public void setVoteCandidate(VoteCandidate voteCandidate) {
		this.voteCandidate = voteCandidate;
	}

}