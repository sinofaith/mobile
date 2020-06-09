package cn.com.sinofaith.bean;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_qq_ltjl", schema = "")
public class TAutoQqLtjlEntity {
    private long id;
    //    private String fsqq;
//    private String fsqqnc;
    private String fstime;
    //    private String jsqqno;
//    private String jsqqnc;
    private String fslx;
    //    private byte[] fanr;
    private String lujing;
    private String dataType;
    private String insertTime;
    private String uName;
    private String uNumber;
    private long aj_id;
    private String fsfx;
    private String zhxx;
    private String zhnc;
    private String dszh;
    private String dsnc;
    private String qunzhxx;

    public void setNull() {
//        this.fsqq = null;
//        this.fsqqnc = null;
        this.fstime = null;
//        this.jsqqno = null;
//        this.jsqqnc = null;
        this.fslx = null;
//        this.fanr = null;
        this.lujing = null;
        this.uName=null;
        this.uNumber=null;
        this.aj_id = 0;
        this.zhxx = null;
        this.zhnc = null;
        this.dszh = null;
        this.dsnc = null;
        this.qunzhxx = null;
    }

    @Id
    @Column(name = "id", nullable = false,precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "fsqq")
//    public String getFsqq() {
//        return fsqq;
//    }
//
//    public void setFsqq(String fsqq) {
//        this.fsqq = fsqq;
//    }
//
//    @Basic
//    @Column(name = "fsqqnc")
//    public String getFsqqnc() {
//        return fsqqnc;
//    }
//
//    public void setFsqqnc(String fsqqnc) {
//        this.fsqqnc = fsqqnc;
//    }

    @Basic
    @Column(name = "fstime")
    public String getFstime() {
        return fstime;
    }

    public void setFstime(String fstime) {
        this.fstime = fstime;
    }

//    @Basic
//    @Column(name = "jsqqno")
//    public String getJsqqno() {
//        return jsqqno;
//    }
//
//    public void setJsqqno(String jsqqno) {
//        this.jsqqno = jsqqno;
//    }
//
//    @Basic
//    @Column(name = "jsqqnc")
//    public String getJsqqnc() {
//        return jsqqnc;
//    }
//
//    public void setJsqqnc(String jsqqnc) {
//        this.jsqqnc = jsqqnc;
//    }

    @Basic
    @Column(name = "fslx")
    public String getFslx() {
        return fslx;
    }

    public void setFslx(String fslx) {
        if(fslx==null){
            this.fslx = "";
        }else {
            this.fslx = fslx;
        }
    }

//    @Basic
//    @Column(name = "fanr")
//    public byte[] getFanr() {
//        return fanr;
//    }
//
//    public void setFanr(byte[] fanr) {
//        this.fanr = fanr;
//    }

    @Basic
    @Column(name = "lujing")
    public String getLujing() {
        return lujing;
    }

    public void setLujing(String lujing) {
        if(lujing==null){
            this.lujing = "";
        }else {
            this.lujing = lujing;
        }
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
    @Column(name = "insert_time",length = 19)
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getFsfx() {
        return fsfx;
    }

    public void setFsfx(String fsfx) {
        if(fsfx==null){
            this.fsfx = "";
        }else {
            this.fsfx = fsfx;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoQqLtjlEntity that = (TAutoQqLtjlEntity) o;

        if (id != that.id) return false;
//        if (fsqq != null ? !fsqq.equals(that.fsqq) : that.fsqq != null) return false;
//        if (fsqqnc != null ? !fsqqnc.equals(that.fsqqnc) : that.fsqqnc != null) return false;
        if (fstime != null ? !fstime.equals(that.fstime) : that.fstime != null) return false;
//        if (jsqqno != null ? !jsqqno.equals(that.jsqqno) : that.jsqqno != null) return false;
//        if (jsqqnc != null ? !jsqqnc.equals(that.jsqqnc) : that.jsqqnc != null) return false;
        if (fslx != null ? !fslx.equals(that.fslx) : that.fslx != null) return false;
//        if (!Arrays.equals(fanr, that.fanr)) return false;
        if (lujing != null ? !lujing.equals(that.lujing) : that.lujing != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
//        result = 31 * result + (fsqq != null ? fsqq.hashCode() : 0);
//        result = 31 * result + (fsqqnc != null ? fsqqnc.hashCode() : 0);
        result = 31 * result + (fstime != null ? fstime.hashCode() : 0);
//        result = 31 * result + (jsqqno != null ? jsqqno.hashCode() : 0);
//        result = 31 * result + (jsqqnc != null ? jsqqnc.hashCode() : 0);
        result = 31 * result + (fslx != null ? fslx.hashCode() : 0);
//        result = 31 * result + Arrays.hashCode(fanr);
        result = 31 * result + (lujing != null ? lujing.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
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
    @Column(name = "u_number")
    public String getuNumber() {
        return uNumber;
    }

    public void setuNumber(String uNumber) {
        this.uNumber = uNumber;
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
    @Column(name="zhxx",length = 200)
    public String getZhxx() {
        return zhxx;
    }

    public void setZhxx(String zhxx) {
        this.zhxx = zhxx;
    }
    @Basic
    @Column(name="zhnc",length = 200)
    public String getZhnc() {
        return zhnc;
    }

    public void setZhnc(String zhnc) {
        this.zhnc = zhnc;
    }
    @Basic
    @Column(name="dszh",length = 200)
    public String getDszh() {
        return dszh;
    }

    public void setDszh(String dszh) {
        this.dszh = dszh;
    }
    @Basic
    @Column(name="dsnc",length = 200)
    public String getDsnc() {
        return dsnc;
    }

    public void setDsnc(String dsnc) {
        this.dsnc = dsnc;
    }
    @Basic
    @Column(name="qunzhxx",length = 200)
    public String getQunzhxx() {
        return qunzhxx;
    }

    public void setQunzhxx(String qunzhxx) {
        this.qunzhxx = qunzhxx;
    }

    @Override
    public String toString() {
        return "TAutoQqLtjlEntity{" +
                "id=" + id +
                ", fstime='" + fstime + '\'' +
                ", fslx='" + fslx + '\'' +
                ", lujing='" + lujing + '\'' +
                ", dataType='" + dataType + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", uName='" + uName + '\'' +
                ", uNumber='" + uNumber + '\'' +
                ", aj_id=" + aj_id +
                ", fsfx='" + fsfx + '\'' +
                ", zhxx='" + zhxx + '\'' +
                ", zhnc='" + zhnc + '\'' +
                ", dszh='" + dszh + '\'' +
                ", dsnc='" + dsnc + '\'' +
                ", qunzhxx='" + qunzhxx + '\'' +
                '}';
    }
}
