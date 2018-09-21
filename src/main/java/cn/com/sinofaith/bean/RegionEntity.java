package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_region")
public class RegionEntity {
    private long regionId =-1;
    private long areaId=-1;
    private long caseId=-1;
//    private String roleName;
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
    @Column(name = "area_Id",nullable = false)
    public long getAreaId(){return  areaId;}

    public void setAreaId(long areaId) {
        this.areaId = areaId;
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
    @Column(name = "case_id")
    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }
//    @Basic
//    @Column(name = "role_name",length = 200)
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "regionId=" + regionId +
                ", areaId='" + areaId + '\'' +
                ", unitId=" + caseId +
//                ", roleName='" + roleName + '\'' +
                ", inserttime='" + inserttime + '\'' +
                '}';
    }
}
