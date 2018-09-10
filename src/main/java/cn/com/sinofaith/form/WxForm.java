package cn.com.sinofaith.form;

/**
 * 微信聊天数据
 */
public class WxForm {
    private long id;
    // 姓名
    private String name;
    // 身份证号码
    private String sfzhm;
    // 手机号码
    private String sjhm;
    // 发送QQ号
    private String fswechatno;
    // 发送qq昵称
    private String fswechatnc;
    // 接收QQ号
    private String jswechatno;
    // 接收qq昵称
    private String jsfriendnc;
    // 所属群号
    private String friendqh;
    // 聊天总次数
    private long num;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getFswechatno() {
        return fswechatno;
    }

    public void setFswechatno(String fswechatno) {
        this.fswechatno = fswechatno;
    }

    public String getFswechatnc() {
        return fswechatnc;
    }

    public void setFswechatnc(String fswechatnc) {
        this.fswechatnc = fswechatnc;
    }

    public String getJswechatno() {
        return jswechatno;
    }

    public void setJswechatno(String jswechatno) {
        this.jswechatno = jswechatno;
    }

    public String getJsfriendnc() {
        return jsfriendnc;
    }

    public void setJsfriendnc(String jsfriendnc) {
        this.jsfriendnc = jsfriendnc;
    }

    public String getFriendqh() {
        return friendqh;
    }

    public void setFriendqh(String friendqh) {
        this.friendqh = friendqh;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "WxForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sfzhm='" + sfzhm + '\'' +
                ", sjhm='" + sjhm + '\'' +
                ", fswechatno='" + fswechatno + '\'' +
                ", fswechatnc='" + fswechatnc + '\'' +
                ", jswechatno='" + jswechatno + '\'' +
                ", jsfriendnc='" + jsfriendnc + '\'' +
                ", friendqh='" + friendqh + '\'' +
                ", num=" + num +
                '}';
    }
}
