package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name="t_case",schema ="" )
public class CaseEntity {
    private long id;
    //案件名
    private String caseName;
    //品牌id
    private long brand_id;
    //所属地区
    private long region_id;

    private String inserttime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="case_num")
    @SequenceGenerator(name="case_num",sequenceName="t_case_id",allocationSize=1,initialValue=1)
    @Column(name="id",nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name="casename",nullable = false,length = 200)
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
    @Basic
    @Column(name="brand_id",nullable = false)
    public long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(long brand_id) {
        this.brand_id = brand_id;
    }
    @Basic
    @Column(name="region_id",nullable = false)
    public long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(long region_id) {
        this.region_id = region_id;
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
