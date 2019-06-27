package com.example.app2.goshpping.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class DataBean {
    /**
     * id : 8289
     * title : 油焖大虾
     * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg
     * collect_num : 1667
     * food_str : 大虾 葱 生姜 植物油 料酒
     * num : 1667
     */
@Id(autoincrement = true)
@Property(nameInDb = "Iid")
    private Long _id;
    private String id;
    private String title;
    private String pic;
    private String collect_num;
    private String food_str;
    private boolean isChex;
    private int num;
    @Generated(hash = 2119036911)
    public DataBean(Long _id, String id, String title, String pic,
            String collect_num, String food_str, boolean isChex, int num) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.collect_num = collect_num;
        this.food_str = food_str;
        this.isChex = isChex;
        this.num = num;
    }
    @Generated(hash = 908697775)
    public DataBean() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getCollect_num() {
        return this.collect_num;
    }
    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }
    public String getFood_str() {
        return this.food_str;
    }
    public void setFood_str(String food_str) {
        this.food_str = food_str;
    }
    public boolean getIsChex() {
        return this.isChex;
    }
    public void setIsChex(boolean isChex) {
        this.isChex = isChex;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "_id=" + _id +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", collect_num='" + collect_num + '\'' +
                ", food_str='" + food_str + '\'' +
                ", isChex=" + isChex +
                ", num=" + num +
                '}';
    }
}
