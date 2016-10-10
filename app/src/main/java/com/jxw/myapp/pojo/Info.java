package com.jxw.myapp.pojo;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Info {
    public Integer infoId;
    public String photoImg;
    public String infoContent;
    public String infoName;
    public String infoDate;
    public Integer infoLike;

    public Info(){}
    public Info(Integer infoId, String infoContent, String infoName, String infoDate,Integer infoLike,String photoImg) {
        this.infoId = infoId;
        this.infoContent = infoContent;
        this.infoName = infoName;
        this.infoDate = infoDate;
        this.infoLike = infoLike;
        this.photoImg = photoImg;
    }


}
