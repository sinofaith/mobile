package cn.com.sinofaith.form;

/**
 * qq聊天数据
 */
public class QqForm {
    private long id;
    // 姓名
    private String name;
    // 身份证号码
    private String sfzhm;
    // 手机号码
    private String sjhm;
    // 发送QQ号
    private String fsqq;
    // 发送qq昵称
    private String fsqqnc;
    // 接收QQ号
    private String jsqqno;
    // 接收qq昵称
    private String jsqqnc;
    // 所属群号
    private String qqfriendqh;
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

    public String getFsqq() {
        return fsqq;
    }

    public void setFsqq(String fsqq) {
        this.fsqq = fsqq;
    }

    public String getFsqqnc() {
        return fsqqnc;
    }

    public void setFsqqnc(String fsqqnc) {
        this.fsqqnc = fsqqnc;
    }

    public String getJsqqno() {
        return jsqqno;
    }

    public void setJsqqno(String jsqqno) {
        this.jsqqno = jsqqno;
    }

    public String getJsqqnc() {
        return jsqqnc;
    }

    public void setJsqqnc(String jsqqnc) {
        this.jsqqnc = jsqqnc;
    }

    public String getQqfriendqh() {
        return qqfriendqh;
    }

    public void setQqfriendqh(String qqfriendqh) {
        this.qqfriendqh = qqfriendqh;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "qqForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sfzhm='" + sfzhm + '\'' +
                ", sjhm='" + sjhm + '\'' +
                ", fsqq='" + fsqq + '\'' +
                ", fsqqnc='" + fsqqnc + '\'' +
                ", jsqqno='" + jsqqno + '\'' +
                ", jsqqnc='" + jsqqnc + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
