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
 * The persistent class for the vote_page_header database table.
 * 
 */
@Entity
@Table(name="vote_page_header")
@NamedQuery(name="VotePageHeader.findAll", query="SELECT v FROM VotePageHeader v")
public class VotePageHeader extends FastBaseEntity<VotePageHeader> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(length=500)
	private String image;
	
	@Column(length=500)
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