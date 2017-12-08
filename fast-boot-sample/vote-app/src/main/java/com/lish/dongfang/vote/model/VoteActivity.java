package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 投票活动信息
 * 
 */
@Entity
@Table(name="vote_activity")
@NamedQuery(name="VoteActivity.findAll", query="SELECT v FROM VoteActivity v")
public class VoteActivity extends FastBaseEntity<VoteActivity> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;
	
	@Column(length=100)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private byte status;

	//bi-directional many-to-one association to VoteCandidate
	@OneToMany(mappedBy="voteActivity",fetch=FetchType.LAZY)
	private List<VoteCandidate> voteCandidates;

	//bi-directional many-to-one association to VotePageConfig
	@OneToMany(mappedBy="voteActivity")
	private List<VotePageConfig> votePageConfigs;

	//bi-directional many-to-one association to VotePageEnrollConfig
	@OneToMany(mappedBy="voteActivity")
	private List<VotePageEnrollConfig> votePageEnrollConfigs;

	//bi-directional many-to-one association to VotePageFooter
	@OneToMany(mappedBy="voteActivity")
	private List<VotePageFooter> votePageFooters;

	//bi-directional many-to-one association to VotePageHeader
	@OneToMany(mappedBy="voteActivity")
	private List<VotePageHeader> votePageHeaders;

	//bi-directional many-to-one association to VotePageTheme
	@OneToMany(mappedBy="voteActivity")
	private List<VotePageTheme> votePageThemes;

	public VoteActivity() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<VoteCandidate> getVoteCandidates() {
		return this.voteCandidates;
	}

	public void setVoteCandidates(List<VoteCandidate> voteCandidates) {
		this.voteCandidates = voteCandidates;
	}

	public VoteCandidate addVoteCandidate(VoteCandidate voteCandidate) {
		getVoteCandidates().add(voteCandidate);
		voteCandidate.setVoteActivity(this);

		return voteCandidate;
	}

	public VoteCandidate removeVoteCandidate(VoteCandidate voteCandidate) {
		getVoteCandidates().remove(voteCandidate);
		voteCandidate.setVoteActivity(null);

		return voteCandidate;
	}

	public List<VotePageConfig> getVotePageConfigs() {
		return this.votePageConfigs;
	}

	public void setVotePageConfigs(List<VotePageConfig> votePageConfigs) {
		this.votePageConfigs = votePageConfigs;
	}

	public VotePageConfig addVotePageConfig(VotePageConfig votePageConfig) {
		getVotePageConfigs().add(votePageConfig);
		votePageConfig.setVoteActivity(this);

		return votePageConfig;
	}

	public VotePageConfig removeVotePageConfig(VotePageConfig votePageConfig) {
		getVotePageConfigs().remove(votePageConfig);
		votePageConfig.setVoteActivity(null);

		return votePageConfig;
	}

	public List<VotePageEnrollConfig> getVotePageEnrollConfigs() {
		return this.votePageEnrollConfigs;
	}

	public void setVotePageEnrollConfigs(List<VotePageEnrollConfig> votePageEnrollConfigs) {
		this.votePageEnrollConfigs = votePageEnrollConfigs;
	}

	public VotePageEnrollConfig addVotePageEnrollConfig(VotePageEnrollConfig votePageEnrollConfig) {
		getVotePageEnrollConfigs().add(votePageEnrollConfig);
		votePageEnrollConfig.setVoteActivity(this);

		return votePageEnrollConfig;
	}

	public VotePageEnrollConfig removeVotePageEnrollConfig(VotePageEnrollConfig votePageEnrollConfig) {
		getVotePageEnrollConfigs().remove(votePageEnrollConfig);
		votePageEnrollConfig.setVoteActivity(null);

		return votePageEnrollConfig;
	}

	public List<VotePageFooter> getVotePageFooters() {
		return this.votePageFooters;
	}

	public void setVotePageFooters(List<VotePageFooter> votePageFooters) {
		this.votePageFooters = votePageFooters;
	}

	public VotePageFooter addVotePageFooter(VotePageFooter votePageFooter) {
		getVotePageFooters().add(votePageFooter);
		votePageFooter.setVoteActivity(this);

		return votePageFooter;
	}

	public VotePageFooter removeVotePageFooter(VotePageFooter votePageFooter) {
		getVotePageFooters().remove(votePageFooter);
		votePageFooter.setVoteActivity(null);

		return votePageFooter;
	}

	public List<VotePageHeader> getVotePageHeaders() {
		return this.votePageHeaders;
	}

	public void setVotePageHeaders(List<VotePageHeader> votePageHeaders) {
		this.votePageHeaders = votePageHeaders;
	}

	public VotePageHeader addVotePageHeader(VotePageHeader votePageHeader) {
		getVotePageHeaders().add(votePageHeader);
		votePageHeader.setVoteActivity(this);

		return votePageHeader;
	}

	public VotePageHeader removeVotePageHeader(VotePageHeader votePageHeader) {
		getVotePageHeaders().remove(votePageHeader);
		votePageHeader.setVoteActivity(null);

		return votePageHeader;
	}

	public List<VotePageTheme> getVotePageThemes() {
		return this.votePageThemes;
	}

	public void setVotePageThemes(List<VotePageTheme> votePageThemes) {
		this.votePageThemes = votePageThemes;
	}

	public VotePageTheme addVotePageTheme(VotePageTheme votePageTheme) {
		getVotePageThemes().add(votePageTheme);
		votePageTheme.setVoteActivity(this);

		return votePageTheme;
	}

	public VotePageTheme removeVotePageTheme(VotePageTheme votePageTheme) {
		getVotePageThemes().remove(votePageTheme);
		votePageTheme.setVoteActivity(null);

		return votePageTheme;
	}

}