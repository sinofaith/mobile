package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name="t_case",schema ="" )
public class CaseEntity {
    private long caseId=-1;
    //案件名
    private String caseName;
    //创建人
    private String creater;
    private long brandId=-1;
    private String inserttime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="case_num")
    @SequenceGenerator(name="case_num",sequenceName="t_case_id",allocationSize=1,initialValue=1)
    @Column(name="case_id",nullable = false)
    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }
    @Basic
    @Column(name="case_name",nullable = false,length = 200)
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
    @Basic
    @Column(name="creater",nullable = false)
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }


    @Basic
    @Column(name="brand_id",nullable = false)
    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long unitId) {
        this.brandId = unitId;
    }

    @Basic
    @Column(name="inserttime",nullable = false,length = 200)
    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }


}
