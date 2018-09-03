package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_wechat_zhxx", schema = "")
public class TAutoWechatZhxxEntity {
    private int id;
    private String name;
    private String sfzhm;
    private String sjhm;
    private String sex;
    private String wechatno;
    private String nicheng;
    private String qq;
    private String dataType;
    private String insertTime;
    private String miyao;
    private String mysflx;
    private String email;
    private String sheng;
    private String shi;
    private String wxh;

    public void setNull() {
        this.name = null;
        this.sfzhm = null;
        this.sjhm = null;
        this.sex = null;
        this.wechatno = null;
        this.nicheng = null;
        this.qq = null;
        this.miyao = null;
        this.mysflx = null;
        this.email = null;
        this.sheng = null;
        this.shi = null;
        this.wxh = null;
        this.gxqm = null;
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
    @Column(name = "nicheng")
    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
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

        TAutoWechatZhxxEntity that = (TAutoWechatZhxxEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sfzhm != null ? !sfzhm.equals(that.sfzhm) : that.sfzhm != null) return false;
        if (sjhm != null ? !sjhm.equals(that.sjhm) : that.sjhm != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (wechatno != null ? !wechatno.equals(that.wechatno) : that.wechatno != null) return false;
        if (nicheng != null ? !nicheng.equals(that.nicheng) : that.nicheng != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
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
        result = 31 * result + (nicheng != null ? nicheng.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "miyao")
    public String getMiyao() {
        return miyao;
    }

    public void setMiyao(String miyao) {
        this.miyao = miyao;
    }

    @Basic
    @Column(name = "mysflx")
    public String getMysflx() {
        return mysflx;
    }

    public void setMysflx(String mysflx) {
        this.mysflx = mysflx;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "sheng")
    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    @Basic
    @Column(name = "shi")
    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    @Basic
    @Column(name = "wxh")
    public String getWxh() {
        return wxh;
    }

    public void setWxh(String wxh) {
        this.wxh = wxh;
    }

    private String gxqm;

    @Basic
    @Column(name = "gxqm")
    public String getGxqm() {
        return gxqm;
    }

    public void setGxqm(String gxqm) {
        this.gxqm = gxqm;
    }
}