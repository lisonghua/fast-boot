package com.lish.dongfang.vote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.lish.dongfang.core.FastBaseEntity;


/**
 * 投票页面footer配置
 * 
 */
@Entity
@Table(name="vote_page_footer")
@NamedQuery(name="VotePageFooter.findAll", query="SELECT v FROM VotePageFooter v")
public class VotePageFooter extends FastBaseEntity<VotePageFooter> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文本的超链接
	 */
	@Column(length=500)
	private String link;

	/**
	 * 显示文本
	 */
	@Column(length=200)
	private String text;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	public VotePageFooter() {
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

}