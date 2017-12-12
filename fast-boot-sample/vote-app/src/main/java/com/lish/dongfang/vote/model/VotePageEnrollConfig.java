package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 报名页面配置
 * 
 */
@Entity
@Table(name="vote_page_enroll_config")
@NamedQuery(name="VotePageEnrollConfig.findAll", query="SELECT v FROM VotePageEnrollConfig v")
public class VotePageEnrollConfig extends FastBaseEntity<VotePageEnrollConfig> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址是否显示
	 */
	@Column(name="address_display")
	private byte addressDisplay;

	/**
	 * 地址是否必填
	 */
	@Column(name="address_required")
	private byte addressRequired;

	/**
	 * 描述是否显示
	 */
	@Column(name="desc_display")
	private byte descDisplay;

	/**
	 * 描述是否必填
	 */
	@Column(name="desc_required")
	private byte descRequired;

	/**
	 * 报名是否需要审核
	 */
	@Column(name="enable_audit")
	private byte enableAudit;

	/**
	 * 候选人详情首页最大图片数量，报名页图片上传数量
	 */
	@Column(name="max_cover_count")
	private byte maxCoverCount;

	/**
	 * 设置报名页面姓名字段显示名
	 */
	@Column(name="name_label",length=20)
	private String nameLabel;

	/**
	 * 电话是否显示
	 */
	@Column(name="tel_display")
	private byte telDisplay;

	/**
	 * 电话是否必填
	 */
	@Column(name="tel_required")
	private byte telRequired;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	//bi-directional many-to-one association to VotePageEnrollCustomize
	@OneToMany(mappedBy="votePageEnrollConfig",fetch=FetchType.LAZY)
	private List<VotePageEnrollCustomize> votePageEnrollCustomizes;

	public VotePageEnrollConfig() {
	}

	public byte getAddressDisplay() {
		return this.addressDisplay;
	}

	public void setAddressDisplay(byte addressDisplay) {
		this.addressDisplay = addressDisplay;
	}

	public byte getAddressRequired() {
		return this.addressRequired;
	}

	public void setAddressRequired(byte addressRequired) {
		this.addressRequired = addressRequired;
	}

	public byte getDescDisplay() {
		return this.descDisplay;
	}

	public void setDescDisplay(byte descDisplay) {
		this.descDisplay = descDisplay;
	}

	public byte getDescRequired() {
		return this.descRequired;
	}

	public void setDescRequired(byte descRequired) {
		this.descRequired = descRequired;
	}

	public byte getEnableAudit() {
		return this.enableAudit;
	}

	public void setEnableAudit(byte enableAudit) {
		this.enableAudit = enableAudit;
	}

	public byte getMaxCoverCount() {
		return this.maxCoverCount;
	}

	public void setMaxCoverCount(byte maxCoverCount) {
		this.maxCoverCount = maxCoverCount;
	}

	public String getNameLabel() {
		return this.nameLabel;
	}

	public void setNameLabel(String nameLabel) {
		this.nameLabel = nameLabel;
	}

	public byte getTelDisplay() {
		return this.telDisplay;
	}

	public void setTelDisplay(byte telDisplay) {
		this.telDisplay = telDisplay;
	}

	public byte getTelRequired() {
		return this.telRequired;
	}

	public void setTelRequired(byte telRequired) {
		this.telRequired = telRequired;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

	public List<VotePageEnrollCustomize> getVotePageEnrollCustomizes() {
		return this.votePageEnrollCustomizes;
	}

	public void setVotePageEnrollCustomizes(List<VotePageEnrollCustomize> votePageEnrollCustomizes) {
		this.votePageEnrollCustomizes = votePageEnrollCustomizes;
	}

	public VotePageEnrollCustomize addVotePageEnrollCustomize(VotePageEnrollCustomize votePageEnrollCustomize) {
		getVotePageEnrollCustomizes().add(votePageEnrollCustomize);
		votePageEnrollCustomize.setVotePageEnrollConfig(this);

		return votePageEnrollCustomize;
	}

	public VotePageEnrollCustomize removeVotePageEnrollCustomize(VotePageEnrollCustomize votePageEnrollCustomize) {
		getVotePageEnrollCustomizes().remove(votePageEnrollCustomize);
		votePageEnrollCustomize.setVotePageEnrollConfig(null);

		return votePageEnrollCustomize;
	}

}