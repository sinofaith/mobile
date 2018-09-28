package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_role")
public class RoleEntity {
    private long role_id;
    private String role_name;
    private String role;
    private String insertTime;
    private long region_id;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="role_num")
    @SequenceGenerator(name="role_num",sequenceName="SEQ_T_ROLE_ID",allocationSize=1,initialValue=1)
    @Column(name="role_id",nullable = false)
    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    @Basic
    @Column(name = "role_name",nullable = false)
    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Basic
    @Column(name = "role",nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "insertTime",length = 19)
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Basic
    @Column(name = "region_id")
    public long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(long region_id) {
        this.region_id = region_id;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role='" + role + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", region_id=" + region_id +
                '}';
    }
}
