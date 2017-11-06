package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 候选人详情自定义信息
 * 
 */
@Entity
@Table(name="vote_candidate_customize")
@NamedQuery(name="VoteCandidateCustomize.findAll", query="SELECT v FROM VoteCandidateCustomize v")
public class VoteCandidateCustomize extends FastBaseEntity<VoteCandidateCustomize> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性值
	 */
	@Lob
	@Column(name="attribute_value")
	private String attributeValue;

	@Column(name="enroll_customize_id")
	private BigInteger enrollCustomizeId;

	//bi-directional many-to-one association to VoteCandidateDetail
	@ManyToOne
	@JoinColumn(name="vote_candidate_detail_id")
	private VoteCandidateDetail voteCandidateDetail;

	public VoteCandidateCustomize() {
	}

	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public BigInteger getEnrollCustomizeId() {
		return this.enrollCustomizeId;
	}

	public void setEnrollCustomizeId(BigInteger enrollCustomizeId) {
		this.enrollCustomizeId = enrollCustomizeId;
	}

	public VoteCandidateDetail getVoteCandidateDetail() {
		return this.voteCandidateDetail;
	}

	public void setVoteCandidateDetail(VoteCandidateDetail voteCandidateDetail) {
		this.voteCandidateDetail = voteCandidateDetail;
	}

}