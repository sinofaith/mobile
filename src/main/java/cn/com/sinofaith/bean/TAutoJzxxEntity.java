package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_jzxx", schema = "", catalog = "")
public class TAutoJzxxEntity {
    private int id;
    private String name;
    private String biem;
    private String sex;
    private String zjhm;
    private String sjhm;
    private String mac;
    private String yhsbm;
    private String sbsbm;
    private String sjxh;
    private String gzdw;
    private String xzzqh;
    private String xzz;
    private String hjdqh;
    private String hjd;
    private String beizhu;
    private String zjlx;
    private String cjsj;
    private String sbbh;
    private String daorusj;
    private String gsd;
    private String dataType;
    private String insertTime;

    public void setNull(){
        this.biem = null;
        this.sex = null;
        this.zjhm = null;
        this.sjhm = null;
        this.mac = null;
        this.yhsbm = null;
        this.sbsbm = null;
        this.sjxh = null;
        this.gzdw = null;
        this.xzzqh = null;
        this.xzz = null;
        this.hjdqh = null;
        this.hjd = null;
        this.beizhu = null;
        this.zjlx = null;
        this.cjsj = null;
        this.sbbh = null;
        this.daorusj = null;
        this.gsd = null;
        this.insertTime = null;
        this.iccid = null;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    private String iccid;

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
    @Column(name = "biem")
    public String getBiem() {
        return biem;
    }

    public void setBiem(String biem) {
        this.biem = biem;
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
    @Column(name = "zjhm")
    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
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
    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name = "yhsbm")
    public String getYhsbm() {
        return yhsbm;
    }

    public void setYhsbm(String yhsbm) {
        this.yhsbm = yhsbm;
    }

    @Basic
    @Column(name = "sbsbm")
    public String getSbsbm() {
        return sbsbm;
    }

    public void setSbsbm(String sbsbm) {
        this.sbsbm = sbsbm;
    }

    @Basic
    @Column(name = "sjxh")
    public String getSjxh() {
        return sjxh;
    }

    public void setSjxh(String sjxh) {
        this.sjxh = sjxh;
    }

    @Basic
    @Column(name = "gzdw")
    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    @Basic
    @Column(name = "xzzqh")
    public String getXzzqh() {
        return xzzqh;
    }

    public void setXzzqh(String xzzqh) {
        this.xzzqh = xzzqh;
    }

    @Basic
    @Column(name = "xzz")
    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    @Basic
    @Column(name = "hjdqh")
    public String getHjdqh() {
        return hjdqh;
    }

    public void setHjdqh(String hjdqh) {
        this.hjdqh = hjdqh;
    }

    @Basic
    @Column(name = "hjd")
    public String getHjd() {
        return hjd;
    }

    public void setHjd(String hjd) {
        this.hjd = hjd;
    }

    @Basic
    @Column(name = "beizhu")
    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Basic
    @Column(name = "zjlx")
    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    @Basic
    @Column(name = "cjsj")
    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    @Basic
    @Column(name = "sbbh")
    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    @Basic
    @Column(name = "daorusj")
    public String getDaorusj() {
        return daorusj;
    }

    public void setDaorusj(String daorusj) {
        this.daorusj = daorusj;
    }

    @Basic
    @Column(name = "gsd")
    public String getGsd() {
        return gsd;
    }

    public void setGsd(String gsd) {
        this.gsd = gsd;
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

        TAutoJzxxEntity that = (TAutoJzxxEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (biem != null ? !biem.equals(that.biem) : that.biem != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (zjhm != null ? !zjhm.equals(that.zjhm) : that.zjhm != null) return false;
        if (sjhm != null ? !sjhm.equals(that.sjhm) : that.sjhm != null) return false;
        if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
        if (yhsbm != null ? !yhsbm.equals(that.yhsbm) : that.yhsbm != null) return false;
        if (sbsbm != null ? !sbsbm.equals(that.sbsbm) : that.sbsbm != null) return false;
        if (sjxh != null ? !sjxh.equals(that.sjxh) : that.sjxh != null) return false;
        if (gzdw != null ? !gzdw.equals(that.gzdw) : that.gzdw != null) return false;
        if (xzzqh != null ? !xzzqh.equals(that.xzzqh) : that.xzzqh != null) return false;
        if (xzz != null ? !xzz.equals(that.xzz) : that.xzz != null) return false;
        if (hjdqh != null ? !hjdqh.equals(that.hjdqh) : that.hjdqh != null) return false;
        if (hjd != null ? !hjd.equals(that.hjd) : that.hjd != null) return false;
        if (beizhu != null ? !beizhu.equals(that.beizhu) : that.beizhu != null) return false;
        if (zjlx != null ? !zjlx.equals(that.zjlx) : that.zjlx != null) return false;
        if (cjsj != null ? !cjsj.equals(that.cjsj) : that.cjsj != null) return false;
        if (sbbh != null ? !sbbh.equals(that.sbbh) : that.sbbh != null) return false;
        if (daorusj != null ? !daorusj.equals(that.daorusj) : that.daorusj != null) return false;
        if (gsd != null ? !gsd.equals(that.gsd) : that.gsd != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (biem != null ? biem.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (zjhm != null ? zjhm.hashCode() : 0);
        result = 31 * result + (sjhm != null ? sjhm.hashCode() : 0);
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (yhsbm != null ? yhsbm.hashCode() : 0);
        result = 31 * result + (sbsbm != null ? sbsbm.hashCode() : 0);
        result = 31 * result + (sjxh != null ? sjxh.hashCode() : 0);
        result = 31 * result + (gzdw != null ? gzdw.hashCode() : 0);
        result = 31 * result + (xzzqh != null ? xzzqh.hashCode() : 0);
        result = 31 * result + (xzz != null ? xzz.hashCode() : 0);
        result = 31 * result + (hjdqh != null ? hjdqh.hashCode() : 0);
        result = 31 * result + (hjd != null ? hjd.hashCode() : 0);
        result = 31 * result + (beizhu != null ? beizhu.hashCode() : 0);
        result = 31 * result + (zjlx != null ? zjlx.hashCode() : 0);
        result = 31 * result + (cjsj != null ? cjsj.hashCode() : 0);
        result = 31 * result + (sbbh != null ? sbbh.hashCode() : 0);
        result = 31 * result + (daorusj != null ? daorusj.hashCode() : 0);
        result = 31 * result + (gsd != null ? gsd.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }
}
