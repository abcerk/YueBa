package com.yueba.entity;

import java.io.Serializable;
import java.util.ArrayList;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class UserDynamic extends BmobObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private String content;
	private String time;
	private int commentNum;
	private int likeNum;
	private boolean hasLike;
	private String userId;
	private ArrayList <String> imageUrls;
	private BmobRelation dynamicComments;
	

	public UserDynamic() {
		// TODO Auto-generated constructor stub
	}

	public UserDynamic( String content,
			String time, int commentNum, int likeNum, boolean hasLike) {

		this.content = content;
		this.time = time;
		this.commentNum = commentNum;
		this.likeNum = likeNum;
		this.hasLike = hasLike;
	}

	public UserDynamic(String content,
			String time, int commentNum, int likeNum) {
		this.content = content;
		this.time = time;
		this.commentNum = commentNum;
		this.likeNum = likeNum;
	}
	
	
	public BmobRelation getDynamicComments() {
		return dynamicComments;
	}

	public void setDynamicComments(BmobRelation dynamicComments) {
		this.dynamicComments = dynamicComments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public  ArrayList <String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(ArrayList<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public boolean isHasLike() {
		return hasLike;
	}

	public void setHasLike(boolean hasLike) {
		this.hasLike = hasLike;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

}
