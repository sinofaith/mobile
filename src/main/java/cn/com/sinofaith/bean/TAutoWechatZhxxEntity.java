package cn.com.sinofaith.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_wechat_zhxx", schema = "")
public class TAutoWechatZhxxEntity {
    private long id;
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
    private String gxqm;
    private long aj_id;

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
        int result = (int) id;
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

    @Basic
    @Column(name = "gxqm")
    public String getGxqm() {
        return gxqm;
    }

    public void setGxqm(String gxqm) {
        this.gxqm = gxqm;
    }

    @Basic
    @Column(name = "aj_id", nullable = false,precision = 0)
    public long getAj_id() {
        return aj_id;
    }

    public void setAj_id(long aj_id) {
        this.aj_id = aj_id;
    }

    // 数据转换
    public List<TAutoWechatZhxxEntity> distinctToList(List zhxxs) {
        List<TAutoWechatZhxxEntity> zhxxs1 = new ArrayList<>();
        for(int i=0;i<zhxxs.size();i++){
            TAutoWechatZhxxEntity zhxx = new TAutoWechatZhxxEntity();
            Object[] o = (Object[]) zhxxs.get(i);
            if(o[0]==null){zhxx.setName(null);}else{zhxx.setName((String) o[0]);}
            if(o[1]==null){zhxx.setSfzhm(null);}else{zhxx.setSfzhm((String) o[1]);}
            if(o[2]==null){zhxx.setSjhm(null);}else{zhxx.setSjhm((String) o[2]);}
            if(o[3]==null){zhxx.setSex(null);}else{zhxx.setSex((String) o[3]);}
            if(o[4]==null){zhxx.setWxh(null);}else{zhxx.setWxh((String) o[4]);}
            if(o[5]==null){zhxx.setNicheng(null);}else{zhxx.setNicheng((String) o[5]);}
            if(o[6]==null){zhxx.setQq(null);}else{zhxx.setQq((String) o[6]);}
            if(o[7]==null){zhxx.setEmail(null);}else{zhxx.setEmail((String) o[7]);}
            if(o[8]==null){zhxx.setGxqm(null);}else{zhxx.setGxqm((String) o[8]);}
            zhxxs1.add(zhxx);
        }
        return zhxxs1;
    }
}
