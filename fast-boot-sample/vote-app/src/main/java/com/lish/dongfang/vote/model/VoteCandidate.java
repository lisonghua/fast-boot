package com.lish.dongfang.vote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	private byte sex;
	
	private int age;

	/**
	 * 编号
	 */
	private String number;

	private byte status;

	@Column(name="vote_count")
	private int voteCount;

	//bi-directional many-to-one association to VoteActivity
	@JsonIgnore//忽略jackson转json发生时对象有关联关系造成无限循环堆栈溢出错误
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	//bi-directional many-to-one association to VoteCandidateDetail
	@JsonIgnore//忽略jackson转json发生时对象有关联关系造成无限循环堆栈溢出错误
	@OneToOne(mappedBy="voteCandidate",fetch=FetchType.LAZY)
	private VoteCandidateDetail voteCandidateDetail;
	
	//bi-directional many-to-one association to VoteCandidateCustomize
	@JsonIgnore//忽略jackson转json发生时对象有关联关系造成无限循环堆栈溢出错误
	@OneToMany(mappedBy="voteCandidate",fetch=FetchType.LAZY)
	private List<VoteCandidateCustomize> voteCandidateCustomizes;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public VoteCandidateDetail getVoteCandidateDetail() {
		return this.voteCandidateDetail;
	}

	public void setVoteCandidateDetail(VoteCandidateDetail voteCandidateDetail) {
		this.voteCandidateDetail = voteCandidateDetail;
	}
	
	public List<VoteCandidateCustomize> getVoteCandidateCustomizes() {
		return this.voteCandidateCustomizes;
	}

	public void setVoteCandidateCustomizes(List<VoteCandidateCustomize> voteCandidateCustomizes) {
		this.voteCandidateCustomizes = voteCandidateCustomizes;
	}
	
	public VoteCandidateCustomize addVoteCandidateCustomize(VoteCandidateCustomize voteCandidateCustomize) {
		getVoteCandidateCustomizes().add(voteCandidateCustomize);
		voteCandidateCustomize.setVoteCandidate(this);

		return voteCandidateCustomize;
	}

	public VoteCandidateCustomize removeVoteCandidateCustomize(VoteCandidateCustomize voteCandidateCustomize) {
		getVoteCandidateCustomizes().remove(voteCandidateCustomize);
		voteCandidateCustomize.setVoteCandidate(null);

		return voteCandidateCustomize;
	}
}