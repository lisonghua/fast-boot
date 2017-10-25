package com.lish.dongfang.vote.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 投票页面整体样式配置
 * 
 */
@Entity
@Table(name="vote_page_theme")
@NamedQuery(name="VotePageTheme.findAll", query="SELECT v FROM VotePageTheme v")
public class VotePageTheme extends com.sj.common.base.domain.BaseEntity<VotePageTheme> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 背景图片
	 */
	private String background;

	/**
	 * 边框
	 */
	private String border;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 样式类型
	 */
	@Column(name="theme_type")
	private String themeType;

	//bi-directional many-to-one association to VoteActivity
	@ManyToOne
	@JoinColumn(name="vote_activity_id")
	private VoteActivity voteActivity;

	public VotePageTheme() {
	}

	public String getBackground() {
		return this.background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBorder() {
		return this.border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getThemeType() {
		return this.themeType;
	}

	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}

	public VoteActivity getVoteActivity() {
		return this.voteActivity;
	}

	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

}