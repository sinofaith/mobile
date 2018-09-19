package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_region")
public class RegionEntity {
    private long regionId =-1;
    private String regionName;
    private long unitId=-1;
    private String roleName;
    private String inserttime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="region_num")
    @SequenceGenerator(name="region_num",sequenceName="SEQ_T_REGION_ID",allocationSize=1,initialValue=1)
    @Column(name="region_id",nullable = false)
    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }
    @Basic
    @Column(name = "region_name",length = 200)
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
    @Basic
    @Column(name = "unit_id")
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
    @Basic
    @Column(name = "role_name",length = 200)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", unitId=" + unitId +
                ", roleName='" + roleName + '\'' +
                ", inserttime='" + inserttime + '\'' +
                '}';
    }
}
