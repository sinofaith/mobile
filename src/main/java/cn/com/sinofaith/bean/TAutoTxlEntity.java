package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_txl", schema = "")
public class TAutoTxlEntity {
    private int id;
    private String uName;
    private String uNumber;
    private String pName;
    private String pNumber1;
    private String insertTime;
    private String dataType;
    private String pNumber2;

    public void setNull() {
        this.uName = null;
        this.uNumber = null;
        this.pName = null;
        this.pNumber1 = null;
        this.pNumber2 = null;
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
    @Column(name = "p_name")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Basic
    @Column(name = "p_number1")
    public String getpNumber1() {
        return pNumber1;
    }

    public void setpNumber1(String pNumber1) {
        this.pNumber1 = pNumber1;
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
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "p_number2")
    public String getpNumber2() {
        return pNumber2;
    }

    public void setpNumber2(String pNumber2) {
        this.pNumber2 = pNumber2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoTxlEntity that = (TAutoTxlEntity) o;

        if (id != that.id) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uNumber != null ? !uNumber.equals(that.uNumber) : that.uNumber != null) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (pNumber1 != null ? !pNumber1.equals(that.pNumber1) : that.pNumber1 != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (pNumber2 != null ? !pNumber2.equals(that.pNumber2) : that.pNumber2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uNumber != null ? uNumber.hashCode() : 0);
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (pNumber1 != null ? pNumber1.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (pNumber2 != null ? pNumber2.hashCode() : 0);
        return result;
    }
}
