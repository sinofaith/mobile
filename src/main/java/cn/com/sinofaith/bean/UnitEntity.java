package cn.com.sinofaith.bean;

import javax.persistence.*;


@Entity
@Table(name = "t_unit")
public class UnitEntity {
    private long unitId=-1;
    private String unitName;
//    private long brandId=-1;
    private String inserttime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="unit_num")
    @SequenceGenerator(name="unit_num",sequenceName="t_unit_id",allocationSize=1,initialValue=1)
    @Column(name="unit_id",nullable = false)
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
    @Basic
    @Column(name = "unit_name",length = 200)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
//    @Basic
//    @Column(name = "brand_id")
//    public long getBrandId() {
//        return brandId;
//    }
//
//    public void setBrandId(long brandId) {
//        this.brandId = brandId;
//    }
    @Basic
    @Column(name="inserttime",length = 19)
    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }
}
