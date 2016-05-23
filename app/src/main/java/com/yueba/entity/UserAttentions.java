package com.yueba.entity;

import cn.bmob.v3.BmobObject;

public class UserAttentions extends BmobObject{

	private static final long serialVersionUID = -3868378373400023229L;

	private User user;
	private User attentionUser;
	
	public UserAttentions() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAttentionUser() {
		return attentionUser;
	}

	public void setAttentionUser(User attentionUser) {
		this.attentionUser = attentionUser;
	}

	
	

}
