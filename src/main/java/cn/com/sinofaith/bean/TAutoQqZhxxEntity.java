package cn.com.sinofaith.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_qq_zhxx", schema = "")
public class
TAutoQqZhxxEntity {
    private long id;
    private String name;
    private String sfzhm;
    private String sjhm;
    private String sex;
    private String qq;
    private String nicheng;
    private String dataType;
    private String  insertTime;
    private String glzh;
    private String age;
    private String szd;
    private String mima;
    private String birthday;
    private String gxqm;
    private long aj_id;

    @Id
    @Column(name = "id",nullable = false,precision = 0)
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
    @Column(name = "nicheng")
    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
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

    public void setNull() {
        this.name = null;
        this.sfzhm = null;
        this.sjhm = null;
        this.sex = null;
        this.qq = null;
        this.nicheng = null;
        this.glzh = null;
        this.age = null;
        this.szd = null;
        this.mima = null;
        this.birthday = null;
        this.gxqm = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoQqZhxxEntity that = (TAutoQqZhxxEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sfzhm != null ? !sfzhm.equals(that.sfzhm) : that.sfzhm != null) return false;
        if (sjhm != null ? !sjhm.equals(that.sjhm) : that.sjhm != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (nicheng != null ? !nicheng.equals(that.nicheng) : that.nicheng != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;
        if (gxqm != null ? !gxqm.equals(that.gxqm) : that.gxqm != null) return false;

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
        result = 31 * result + (nicheng != null ? nicheng.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "glzh")
    public String getGlzh() {
        return glzh;
    }

    public void setGlzh(String glzh) {
        this.glzh = glzh;
    }

    @Basic
    @Column(name = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "szd")
    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd;
    }

    @Basic
    @Column(name = "mima")
    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    @Basic
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public List<TAutoQqZhxxEntity> distinctToList(List zhxxs){
        List<TAutoQqZhxxEntity> zhxxs1 = new ArrayList<>();
        for(int i=0;i<zhxxs.size();i++){
            TAutoQqZhxxEntity zhxx = new TAutoQqZhxxEntity();
            Object[] o = (Object[]) zhxxs.get(i);
            if(o[0]==null){zhxx.setName(null);}else{zhxx.setName((String) o[0]);}
            if(o[1]==null){zhxx.setSfzhm(null);}else{zhxx.setSfzhm((String) o[1]);}
            if(o[2]==null){zhxx.setSjhm(null);}else{zhxx.setSjhm((String) o[2]);}
            if(o[3]==null){zhxx.setSex(null);}else{zhxx.setSex((String) o[3]);}
            if(o[4]==null){zhxx.setQq(null);}else{zhxx.setQq((String) o[4]);}
            if(o[5]==null){zhxx.setNicheng(null);}else{zhxx.setNicheng((String) o[5]);}
            if(o[6]==null){zhxx.setDataType(null);}else{zhxx.setDataType((String) o[6]);}
            if(o[7]==null){zhxx.setAge(null);}else{zhxx.setAge((String) o[7]);}
            if(o[8]==null){zhxx.setSzd(null);}else{zhxx.setSzd((String) o[8]);}
            if(o[9]==null){zhxx.setBirthday(null);}else{zhxx.setBirthday((String) o[9]);}
            if(o[10]==null){zhxx.setGxqm(null);}else{zhxx.setGxqm((String) o[10]);}
            zhxxs1.add(zhxx);
        }
        return zhxxs1;
    }
}
