package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_wechat_friendsxx", schema = "")
public class TAutoWechatFriendsxxEntity {
    private int id;
    private String name;
    private String sfzhm;
    private String sjhm;
    private String sex;
    private String wechatno;
    private String fdwechatno;
    private String friendnc;
    private String friendsex;
    private String dataType;
    private String insertTime;
    private String friendbz;
    private String friendqm;
    private String friendszd;
    private String friendqh;

    public void setNull() {
        this.name = null;
        this.sfzhm = null;
        this.sjhm = null;
        this.sex = null;
        this.wechatno = null;
        this.fdwechatno = null;
        this.friendnc = null;
        this.friendsex = null;
        this.friendbz = null;
        this.friendqm = null;
        this.friendszd = null;
        this.friendqh = null;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sfzhm")
    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    @Basic
    @Column(name = "sjhm")
    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "wechatno")
    public String getWechatno() {
        return wechatno;
    }

    public void setWechatno(String wechatno) {
        this.wechatno = wechatno;
    }

    @Basic
    @Column(name = "fdwechatno")
    public String getFdwechatno() {
        return fdwechatno;
    }

    public void setFdwechatno(String fdwechatno) {
        this.fdwechatno = fdwechatno;
    }

    @Basic
    @Column(name = "friendnc")
    public String getFriendnc() {
        return friendnc;
    }

    public void setFriendnc(String friendnc) {
        this.friendnc = friendnc;
    }

    @Basic
    @Column(name = "friendsex")
    public String getFriendsex() {
        return friendsex;
    }

    public void setFriendsex(String friendsex) {
        this.friendsex = friendsex;
    }

    @Basic
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "insert_time")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoWechatFriendsxxEntity that = (TAutoWechatFriendsxxEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sfzhm != null ? !sfzhm.equals(that.sfzhm) : that.sfzhm != null) return false;
        if (sjhm != null ? !sjhm.equals(that.sjhm) : that.sjhm != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (wechatno != null ? !wechatno.equals(that.wechatno) : that.wechatno != null) return false;
        if (fdwechatno != null ? !fdwechatno.equals(that.fdwechatno) : that.fdwechatno != null) return false;
        if (friendnc != null ? !friendnc.equals(that.friendnc) : that.friendnc != null) return false;
        if (friendsex != null ? !friendsex.equals(that.friendsex) : that.friendsex != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sfzhm != null ? sfzhm.hashCode() : 0);
        result = 31 * result + (sjhm != null ? sjhm.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (wechatno != null ? wechatno.hashCode() : 0);
        result = 31 * result + (fdwechatno != null ? fdwechatno.hashCode() : 0);
        result = 31 * result + (friendnc != null ? friendnc.hashCode() : 0);
        result = 31 * result + (friendsex != null ? friendsex.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "friendbz")
    public String getFriendbz() {
        return friendbz;
    }

    public void setFriendbz(String friendbz) {
        this.friendbz = friendbz;
    }

    @Basic
    @Column(name = "friendqm")
    public String getFriendqm() {
        return friendqm;
    }

    public void setFriendqm(String friendqm) {
        this.friendqm = friendqm;
    }

    @Basic
    @Column(name = "friendszd")
    public String getFriendszd() {
        return friendszd;
    }

    public void setFriendszd(String friendszd) {
        this.friendszd = friendszd;
    }

    @Basic
    @Column(name = "friendqh")
    public String getFriendqh() {
        return friendqh;
    }

    public void setFriendqh(String friendqh) {
        this.friendqh = friendqh;
    }
}
