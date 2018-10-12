package cn.com.sinofaith.bean;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by ryw on 2016/11/20.
 */
@Entity
@Table(name = "t_auto_wechat_ltjl", schema = "")
public class TAutoWechatLtjlEntity {
    private long id;
    private String fswechatno;
    private String fswechatnc;
    private String fstime;
    private String jswechatno;
    private String jsfriendnc;
    private String fslx;
    private byte[] fanr;
    private String lujing;
    private String dataType;
    private String insertTime;
    private String uNumber;
    private String uName;
    private long aj_id;
    private String fsfx;

    public void setNull() {
        this.fswechatno = null;
        this.fswechatnc = null;
        this.fstime = null;
        this.jswechatno = null;
        this.jsfriendnc = null;
        this.fslx = null;
        this.fanr = null;
        this.lujing = null;
        this.uName=null;
        this.uNumber=null;
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
    @Column(name = "fswechatno")
    public String getFswechatno() {
        return fswechatno;
    }

    public void setFswechatno(String fswechatno) {
        this.fswechatno = fswechatno;
    }

    @Basic
    @Column(name = "fswechatnc")
    public String getFswechatnc() {
        return fswechatnc;
    }

    public void setFswechatnc(String fswechatnc) {
        this.fswechatnc = fswechatnc;
    }

    @Basic
    @Column(name = "fstime")
    public String getFstime() {
        return fstime;
    }

    public void setFstime(String fstime) {
        this.fstime = fstime;
    }

    @Basic
    @Column(name = "jswechatno")
    public String getJswechatno() {
        return jswechatno;
    }

    public void setJswechatno(String jswechatno) {
        this.jswechatno = jswechatno;
    }

    @Basic
    @Column(name = "jsfriendnc")
    public String getJsfriendnc() {
        return jsfriendnc;
    }

    public void setJsfriendnc(String jsfriendnc) {
        this.jsfriendnc = jsfriendnc;
    }

    @Basic
    @Column(name = "fslx")
    public String getFslx() {
        return fslx;
    }

    public void setFslx(String fslx) {
        this.fslx = fslx;
    }

    @Basic
    @Column(name = "fanr")
    public byte[] getFanr() {
        return fanr;
    }

    public void setFanr(byte[] fanr) {
        this.fanr = fanr;
    }

    @Basic
    @Column(name = "lujing")
    public String getLujing() {
        return lujing;
    }

    public void setLujing(String lujing) {
        this.lujing = lujing;
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

        TAutoWechatLtjlEntity that = (TAutoWechatLtjlEntity) o;

        if (id != that.id) return false;
        if (fswechatno != null ? !fswechatno.equals(that.fswechatno) : that.fswechatno != null) return false;
        if (fswechatnc != null ? !fswechatnc.equals(that.fswechatnc) : that.fswechatnc != null) return false;
        if (fstime != null ? !fstime.equals(that.fstime) : that.fstime != null) return false;
        if (jswechatno != null ? !jswechatno.equals(that.jswechatno) : that.jswechatno != null) return false;
        if (jsfriendnc != null ? !jsfriendnc.equals(that.jsfriendnc) : that.jsfriendnc != null) return false;
        if (fslx != null ? !fslx.equals(that.fslx) : that.fslx != null) return false;
        if (!Arrays.equals(fanr, that.fanr)) return false;
        if (lujing != null ? !lujing.equals(that.lujing) : that.lujing != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (fswechatno != null ? fswechatno.hashCode() : 0);
        result = 31 * result + (fswechatnc != null ? fswechatnc.hashCode() : 0);
        result = 31 * result + (fstime != null ? fstime.hashCode() : 0);
        result = 31 * result + (jswechatno != null ? jswechatno.hashCode() : 0);
        result = 31 * result + (jsfriendnc != null ? jsfriendnc.hashCode() : 0);
        result = 31 * result + (fslx != null ? fslx.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(fanr);
        result = 31 * result + (lujing != null ? lujing.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "u_number")
    public String getuNumber() {
        return uNumber;
    }

    public void setuNumber(String uNumber) {
        this.uNumber = uNumber;
    }

    @Basic
    @Column(name = "u_name")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "aj_id", nullable = false,precision = 0)
    public long getAj_id() {
        return aj_id;
    }

    public void setAj_id(long aj_id) {
        this.aj_id = aj_id;
    }

    public String getFsfx() {
        return fsfx;
    }

    public void setFsfx(String fsfx) {
        this.fsfx = fsfx;
    }
}
