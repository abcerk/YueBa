package com.yueba.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import android.graphics.drawable.Drawable;

/**
 * BmobUser中有username  password  Email 三个属性
 *  登陆注册所用的用户名即手机号account
 */
public class User extends BmobUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nickName; // 用户昵称
	private String school; // 学校名字
	private BmobFile avatar; //用户头像
	private String gender;  //性别
	private String birthday; // 生日
	private String hometown; // 家乡
	private String signature; // 个性签名
	private String relationship; // 恋爱状态
	private BmobRelation userDynamics;  //用户动态
	private BmobRelation userComments;  //用户评论
	private int userDynamicNum; //动态数
	private int attentionNum;   //关注人数
	private int fansNum;       //粉丝人数
	private String token;



	public int getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BmobRelation getUserComments() {
		return userComments;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setUserComments(BmobRelation userComments) {
		this.userComments = userComments;
	}

	public int getUserDynamicNum() {
		return userDynamicNum;
	}

	public void setUserDynamicNum(int userDynamicNum) {
		this.userDynamicNum = userDynamicNum;
	}

	public BmobRelation getUserDynamics() {
		return userDynamics;
	}

	public void setUserDynamics(BmobRelation userDynamics) {
		this.userDynamics = userDynamics;
	}

	public BmobFile getAvatar() {
		return avatar;
	}

	public void setAvatar(BmobFile avatar) {
		this.avatar = avatar;
	}



	public User() {

	}

	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}
