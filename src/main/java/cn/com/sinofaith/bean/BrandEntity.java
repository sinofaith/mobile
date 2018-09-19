package cn.com.sinofaith.bean;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_brand",schema = "")
public class BrandEntity {
    private long brandId = -1;
    //品牌名
    private String brandName;
    private String unitName;
    private String inserttime;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="brand_num")
    @SequenceGenerator(name="brand_num",sequenceName="t_brand_id",allocationSize=1,initialValue=1)
    @Column(name="brand_id",nullable = false)
    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }
    @Basic
    @Column(name="brand_name",nullable = false,length = 200)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Basic
    @Column(name="unit_name",nullable = false)
    public String  getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    @Basic
    @Column(name="inserttime",nullable = false,length = 19)
    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

}
