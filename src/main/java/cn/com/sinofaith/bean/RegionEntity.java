package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_region")
public class RegionEntity {
    private long id =-1;
    private String regionName;
    private String inserttime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="region_num")
    @SequenceGenerator(name="region_num",sequenceName="SEQ_T_REGION_ID",allocationSize=1,initialValue=1)
    @Column(name="id",nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "regionname",length = 200)
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    @Basic
    @Column(name = "inserttime",length = 19)
    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }
}
