package cn.com.sinofaith.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_zjxx",schema = "")
public class TZjxxEntity {
    private long id;
    private String zjm; //证据名 手机取证报告文件夹名
    private String zjbb; //手机取证报告版本号
    private String inserttime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "zjm",length = 500,nullable = false)
    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }
    @Basic
    @Column(name="zjbb",length = 100,nullable = false)
    public String getZjbb() {
        return zjbb;
    }

    public void setZjbb(String zjbb) {
        this.zjbb = zjbb;
    }
    @Basic
    @Column(name = "inserttime",length = 19)
    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    public String toString() {
        return "TZjxxEntity{" +
                "id=" + id +
                ", zjm='" + zjm + '\'' +
                ", zjbb='" + zjbb + '\'' +
                ", inserttime='" + inserttime + '\'' +
                '}';
    }
}
