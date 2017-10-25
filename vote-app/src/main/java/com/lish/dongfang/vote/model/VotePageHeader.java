package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vote_page_header database table.
 * 
 */
@Entity
@Table(name="vote_page_header")
@NamedQuery(name="VotePageHeader.findAll", query="SELECT v FROM VotePageHeader v")
public class VotePageHeader extends com.sj.common.base.domain.BaseEntity<VotePageHeader> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String image;

	private String link;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	public VotePageHeader() {
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

}