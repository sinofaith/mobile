package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_thqd", schema = "")
public class TAutoThqdEntity {
    private long id;
    private String uNumber;
    private String pNumber;
    private String callDate;
    private String callTime;
    private String callValue;
    private String callType;
    private String uName;
    private String dataType;
    private String pName;
    private String insertTime;
    private String flg;
    private long aj_id;

    public void setNull() {
        this.uNumber = null;
        this.pNumber = null;
        this.callDate = null;
        this.callTime = null;
        this.callValue = null;
        this.callType = null;
        this.uName = null;
        this.pName = null;
        this.flg = null;
        this.aj_id=0;
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
    @Column(name = "u_number")
    public String getuNumber() {
        return uNumber;
    }

    public void setuNumber(String uNumber) {
        this.uNumber = uNumber;
    }

    @Basic
    @Column(name = "p_number")
    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    @Basic
    @Column(name = "call_date")
    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    @Basic
    @Column(name = "call_time")
    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    @Basic
    @Column(name = "call_value")
    public String getCallValue() {
        return callValue;
    }

    public void setCallValue(String callValue) {
        this.callValue = callValue;
    }

    @Basic
    @Column(name = "call_type")
    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
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
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
    @Column(name = "insert_time")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Basic
    @Column(name = "flg")
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    @Basic
    @Column(name = "aj_id", nullable = false,precision = 0)
    public long getAj_id() {
        return aj_id;
    }

    public void setAj_id(long aj_id) {
        this.aj_id = aj_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAutoThqdEntity that = (TAutoThqdEntity) o;

        if (id != that.id) return false;
        if (uNumber != null ? !uNumber.equals(that.uNumber) : that.uNumber != null) return false;
        if (pNumber != null ? !pNumber.equals(that.pNumber) : that.pNumber != null) return false;
        if (callDate != null ? !callDate.equals(that.callDate) : that.callDate != null) return false;
        if (callTime != null ? !callTime.equals(that.callTime) : that.callTime != null) return false;
        if (callValue != null ? !callValue.equals(that.callValue) : that.callValue != null) return false;
        if (callType != null ? !callType.equals(that.callType) : that.callType != null) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;
        if (flg != null ? !flg.equals(that.flg) : that.flg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (uNumber != null ? uNumber.hashCode() : 0);
        result = 31 * result + (pNumber != null ? pNumber.hashCode() : 0);
        result = 31 * result + (callDate != null ? callDate.hashCode() : 0);
        result = 31 * result + (callTime != null ? callTime.hashCode() : 0);
        result = 31 * result + (callValue != null ? callValue.hashCode() : 0);
        result = 31 * result + (callType != null ? callType.hashCode() : 0);
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        result = 31 * result + (flg != null ? flg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TAutoThqdEntity{" +
                "id=" + id +
                ", uNumber='" + uNumber + '\'' +
                ", pNumber='" + pNumber + '\'' +
                ", callDate='" + callDate + '\'' +
                ", callTime='" + callTime + '\'' +
                ", callValue='" + callValue + '\'' +
                ", callType='" + callType + '\'' +
                ", uName='" + uName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", pName='" + pName + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", flg='" + flg + '\'' +
                ", aj_id=" + aj_id +
                '}';
    }
}
