package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 投票页面footer配置
 * 
 */
@Entity
@Table(name="vote_page_footer")
@NamedQuery(name="VotePageFooter.findAll", query="SELECT v FROM VotePageFooter v")
public class VotePageFooter extends com.sj.common.base.domain.BaseEntity<VotePageFooter> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文本的超链接
	 */
	private String link;

	/**
	 * 显示文本
	 */
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