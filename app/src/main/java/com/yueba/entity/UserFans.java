package com.yueba.entity;

import cn.bmob.v3.BmobObject;

public class UserFans extends BmobObject{

	private static final long serialVersionUID = -8121104060443087805L;

	private User user;
	private User fans;
	
	public UserFans() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFans() {
		return fans;
	}

	public void setFans(User fans) {
		this.fans = fans;
	}

	
	

}
