package cn.com.sinofaith.bean;

import javax.persistence.*;

/**
 * Created by xs on 2018/8/31.
 */
@Entity
@Table(name = "t_auto_dx", schema = "")
public class TAutoDxEntity {
    private long id;
    private String uName;
    private String uNumber;
    private String pName;
    private String pNumber;
    private String content;
    private String insertTime;
    private String sendTime;
    private String sendType;
    private String dataType;
    private long aj_id;

    public void setNull () {
        this.uName = null;
        this.uNumber = null;
        this.pName = null;
        this.pNumber = null;
        this.content = null;
        this.sendTime = null;
        this.sendType = null;
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
    @Column(name = "p_number")
    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "send_time")
    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "send_type")
    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
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

        TAutoDxEntity that = (TAutoDxEntity) o;

        if (id != that.id) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uNumber != null ? !uNumber.equals(that.uNumber) : that.uNumber != null) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (pNumber != null ? !pNumber.equals(that.pNumber) : that.pNumber != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (insertTime != null ? !insertTime.equals(that.insertTime) : that.insertTime != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (sendType != null ? !sendType.equals(that.sendType) : that.sendType != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uNumber != null ? uNumber.hashCode() : 0);
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (pNumber != null ? pNumber.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (insertTime != null ? insertTime.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (sendType != null ? sendType.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }
}
