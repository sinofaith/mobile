package cn.com.sinofaith.form;

/**
 * qq聊天数据
 */
public class QqForm {
    private long id;
    // 发送QQ号
    private String zhnc;
    // 发送qq昵称
    private String zhxx;
    // 接收QQ号
    private String dsnc;
    // 接收qq昵称
    private String dszh;
    // 聊天总次数
    private long num;

    private String u_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZhnc() {
        return zhnc;
    }

    public void setZhnc(String zhnc) {
        this.zhnc = zhnc;
    }

    public String getZhxx() {
        return zhxx;
    }

    public void setZhxx(String zhxx) {
        this.zhxx = zhxx;
    }

    public String getDsnc() {
        return dsnc;
    }

    public void setDsnc(String dsnc) {
        this.dsnc = dsnc;
    }

    public String getDszh() {
        return dszh;
    }

    public void setDszh(String dszh) {
        this.dszh = dszh;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    @Override
    public String toString() {
        return "QqForm{" +
                "id=" + id +
                ", zhnc='" + zhnc + '\'' +
                ", zhxx='" + zhxx + '\'' +
                ", dsnc='" + dsnc + '\'' +
                ", dszh='" + dszh + '\'' +
                ", num=" + num +
                '}';
    }
}
