package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_qq_friendsxx", schema = "")
public class TAutoQqFriendsxxEntity {
    private long id;
    private String name;
    private String sfzhm;
    private String sjhm;
    private String sex;
    private String qq;
    private String fdqq;
    private String friendqqnc;
    private String friendqqsex;
    private String dataType;
    private String insertTime;
    private String qqfriendbz;
    private String qqfriendgxqm;
    private String qqfriendqh;
    private String qqfriendqshf;
    private long aj_id;

    public void setNull() {
        this.name = null;
        this.sfzhm = null;
        this.sjhm = null;
        this.sex = null;
        this.qq = null;
        this.fdqq = null;
        this.friendqqnc = null;
        this.friendqqsex = null;
        this.qqfriendbz = null;
        this.qqfriendgxqm = null;
        this.qqfriendqh = null;
        this.qqfriendqshf = null;
    }

    @Id
    @Column(name = "id", nullable = false,precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "fdqq")
    public String getFdqq() {
        return fdqq;
    }

    public void setFdqq(String fdqq) {
        this.fdqq = fdqq;
    }

    @Basic
    @Column(name = "friendqqnc")
    public String getFriendqqnc() {
        return friendqqnc;
    }

    public void setFriendqqnc(String friendqqnc) {
        this.friendqqnc = friendqqnc;
    }

    @Basic
    @Column(name = "friendqqsex")
    public String getFriendqqsex() {
        return friendqqsex;
    }

    public void setFriendqqsex(String friendqqsex) {
        this.friendqqsex = friendqqsex;
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

    @Basic
    @Column(name = "aj_id", nullable = false,precision = 0)
    public long getAj_id() {
        return aj_id;
    }

    public void setAj_id(long aj_id) {
        this.aj_id = aj_id;
    }

    @Basic
    @Column(name = "qqfriendbz")
    public String getQqfriendbz() {
        return qqfriendbz;
    }

    public void setQqfriendbz(String qqfriendbz) {
        this.qqfriendbz = qqfriendbz;
    }

    @Basic
    @Column(name = "qqfriendgxqm")
    public String getQqfriendgxqm() {
        return qqfriendgxqm;
    }

    public void setQqfriendgxqm(String qqfriendgxqm) {
        this.qqfriendgxqm = qqfriendgxqm;
    }

    @Basic
    @Column(name = "qqfriendqh")
    public String getQqfriendqh() {
        return qqfriendqh;
    }

    public void setQqfriendqh(String qqfriendqh) {
        this.qqfriendqh = qqfriendqh;
    }

    @Basic
    @Column(name = "qqfriendqshf")
    public String getQqfriendqshf() {
        return qqfriendqshf;
    }

    public void setQqfriendqshf(String qqfriendqshf) {
        this.qqfriendqshf = qqfriendqshf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoQqFriendsxxEntity that = (TAutoQqFriendsxxEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sfzhm != null ? !sfzhm.equals(that.sfzhm) : that.sfzhm != null) return false;
        if (sjhm != null ? !sjhm.equals(that.sjhm) : that.sjhm != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (fdqq != null ? !fdqq.equals(that.fdqq) : that.fdqq != null) return false;
        if (friendqqnc != null ? !friendqqnc.equals(that.friendqqnc) : that.friendqqnc != null) return false;
        if (friendqqsex != null ? !friendqqsex.equals(that.friendqqsex) : that.friendqqsex != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sfzhm != null ? sfzhm.hashCode() : 0);
        result = 31 * result + (sjhm != null ? sjhm.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (fdqq != null ? fdqq.hashCode() : 0);
        result = 31 * result + (friendqqnc != null ? friendqqnc.hashCode() : 0);
        result = 31 * result + (friendqqsex != null ? friendqqsex.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }
}
