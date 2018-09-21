package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_area")
public class AreaEntity {
    private long id;
    private long pid;
    private String name;
    private long type;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "pid")
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
    @Basic
    @Column(name = "name",length = 120)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "type")
    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
}
