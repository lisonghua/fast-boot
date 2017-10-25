package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 投票报名页面自定义字段配置
 * 
 */
@Entity
@Table(name="vote_page_enroll_customize")
@NamedQuery(name="VotePageEnrollCustomize.findAll", query="SELECT v FROM VotePageEnrollCustomize v")
public class VotePageEnrollCustomize extends com.sj.common.base.domain.BaseEntity<VotePageEnrollCustomize> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自定义属性名
	 */
	private String attribute;

	/**
	 * 是否显示
	 */
	private byte display;

	/**
	 * 自定义属性页面显示标题
	 */
	private String label;

	/**
	 * 是否必填
	 */
	private byte required;

	//bi-directional many-to-one association to VotePageEnrollConfig
	@ManyToOne
	@JoinColumn(name="enroll_config_id")
	private VotePageEnrollConfig votePageEnrollConfig;

	public VotePageEnrollCustomize() {
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public byte getDisplay() {
		return this.display;
	}

	public void setDisplay(byte display) {
		this.display = display;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public byte getRequired() {
		return this.required;
	}

	public void setRequired(byte required) {
		this.required = required;
	}

	public VotePageEnrollConfig getVotePageEnrollConfig() {
		return this.votePageEnrollConfig;
	}

	public void setVotePageEnrollConfig(VotePageEnrollConfig votePageEnrollConfig) {
		this.votePageEnrollConfig = votePageEnrollConfig;
	}

}