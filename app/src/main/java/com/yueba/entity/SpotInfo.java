package com.yueba.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by abcerk on 2016/5/22.
 */
public class SpotInfo extends BmobObject implements Serializable {


    private static final long serialVersionUID = -8786895919892453059L;
    private int id; // 景点id
    private String spotName; // 景点名称
    private String description; // 景点介绍
    private BmobFile image1; // 景点图片
    private BmobFile image2; // 景点图片
    private BmobFile image3; // 景点图片
    private BmobFile image4; // 景点图片
    private BmobRelation spotComments;

    public BmobRelation getSpotComments() {
        return spotComments;
    }



    public void setSpotComments(BmobRelation spotComments) {
        this.spotComments = spotComments;
    }



    public SpotInfo() {
        // TODO Auto-generated constructor stub
    }



    public SpotInfo(int id, String spotName, String description, int recommend) {
        this.id = id;
        this.spotName = spotName;
        this.description = description;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public BmobFile getImage1() {
        return image1;
    }



    public void setImage1(BmobFile image1) {
        this.image1 = image1;
    }



    public BmobFile getImage2() {
        return image2;
    }



    public void setImage2(BmobFile image2) {
        this.image2 = image2;
    }



    public BmobFile getImage3() {
        return image3;
    }



    public void setImage3(BmobFile image3) {
        this.image3 = image3;
    }



    public BmobFile getImage4() {
        return image4;
    }



    public void setImage4(BmobFile image4) {
        this.image4 = image4;
    }



}
