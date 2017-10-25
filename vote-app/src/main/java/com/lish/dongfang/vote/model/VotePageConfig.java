package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 套票页面全局设置
 * 
 */
@Entity
@Table(name="vote_page_config")
@NamedQuery(name="VotePageConfig.findAll", query="SELECT v FROM VotePageConfig v")
public class VotePageConfig extends com.sj.common.base.domain.BaseEntity<VotePageConfig> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 选手排序规则：1按参与时间倒序；2票数从高到底；3编号从低到高
	 */
	@Column(name="candidate_order_type")
	private String candidateOrderType;

	/**
	 * 每页显示选手数量
	 */
	@Column(name="display_candidate_count")
	private byte displayCandidateCount;

	/**
	 * 是否开启验证码：1开启；0关闭
	 */
	@Column(name="display_captcha")
	private byte displayCaptcha;

	/**
	 * 显示用户投票记录
	 */
	@Column(name="display_vote_history")
	private byte displayVoteHistory;

	/**
	 * 是否开启黑名单：0否；1是
	 */
	@Column(name="enable_backlist")
	private byte enableBacklist;

	/**
	 * 选手每日获取票数上限
	 */
	@Column(name="max_count_day")
	private int maxCountDay;

	/**
	 * 选手每小时获取票数上限
	 */
	@Column(name="max_count_hour")
	private int maxCountHour;

	/**
	 * 是否开发线上报名:1开启；0关闭
	 */
	@Column(name="online_enroll")
	private byte onlineEnroll;

	/**
	 * 结果显示类型：1单排显示；2双排显示
	 */
	@Column(name="result_display_type")
	private byte resultDisplayType;

	/**
	 * 自定义一个账号每天投票数
	 */
	@Column(name="vote_count_customized")
	private byte voteCountCustomized;

	/**
	 * 投票次数限制：1一个账号仅限一票；2一个账号每天一票；3一个账号每天3票
	 */
	@Column(name="vote_count_limit")
	private String voteCountLimit;

	/**
	 * 是否显示投票结果:1开启；0关闭
	 */
	@Column(name="vote_display_result")
	private byte voteDisplayResult;

	/**
	 * 限定投票：1开启限定投票后，用户如果可以多次投票，那么同一个选手只能投一次；0关闭，用户可以自由投票
	 */
	@Column(name="vote_one_person")
	private byte voteOnePerson;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	public VotePageConfig() {
	}

	public String getCandidateOrderType() {
		return this.candidateOrderType;
	}

	public void setCandidateOrderType(String candidateOrderType) {
		this.candidateOrderType = candidateOrderType;
	}

	public byte getDisplayCandidateCount() {
		return this.displayCandidateCount;
	}

	public void setDisplayCandidateCount(byte displayCandidateCount) {
		this.displayCandidateCount = displayCandidateCount;
	}

	public byte getDisplayCaptcha() {
		return this.displayCaptcha;
	}

	public void setDisplayCaptcha(byte displayCaptcha) {
		this.displayCaptcha = displayCaptcha;
	}

	public byte getDisplayVoteHistory() {
		return this.displayVoteHistory;
	}

	public void setDisplayVoteHistory(byte displayVoteHistory) {
		this.displayVoteHistory = displayVoteHistory;
	}

	public byte getEnableBacklist() {
		return this.enableBacklist;
	}

	public void setEnableBacklist(byte enableBacklist) {
		this.enableBacklist = enableBacklist;
	}

	public int getMaxCountDay() {
		return this.maxCountDay;
	}

	public void setMaxCountDay(int maxCountDay) {
		this.maxCountDay = maxCountDay;
	}

	public int getMaxCountHour() {
		return this.maxCountHour;
	}

	public void setMaxCountHour(int maxCountHour) {
		this.maxCountHour = maxCountHour;
	}

	public byte getOnlineEnroll() {
		return this.onlineEnroll;
	}

	public void setOnlineEnroll(byte onlineEnroll) {
		this.onlineEnroll = onlineEnroll;
	}

	public byte getResultDisplayType() {
		return this.resultDisplayType;
	}

	public void setResultDisplayType(byte resultDisplayType) {
		this.resultDisplayType = resultDisplayType;
	}

	public byte getVoteCountCustomized() {
		return this.voteCountCustomized;
	}

	public void setVoteCountCustomized(byte voteCountCustomized) {
		this.voteCountCustomized = voteCountCustomized;
	}

	public String getVoteCountLimit() {
		return this.voteCountLimit;
	}

	public void setVoteCountLimit(String voteCountLimit) {
		this.voteCountLimit = voteCountLimit;
	}

	public byte getVoteDisplayResult() {
		return this.voteDisplayResult;
	}

	public void setVoteDisplayResult(byte voteDisplayResult) {
		this.voteDisplayResult = voteDisplayResult;
	}

	public byte getVoteOnePerson() {
		return this.voteOnePerson;
	}

	public void setVoteOnePerson(byte voteOnePerson) {
		this.voteOnePerson = voteOnePerson;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

}