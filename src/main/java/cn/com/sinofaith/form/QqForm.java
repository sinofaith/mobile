package cn.com.sinofaith.form;

import java.math.BigDecimal;
import java.util.Map;

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

    public QqForm wxmapToForm(Map map){
        QqForm zzf = new QqForm();
        zzf.setZhxx((String) map.get("ZHXX"));
        zzf.setZhnc((String) map.get("ZHNC"));
        zzf.setDszh((String)map.get("DSZH"));
        zzf.setDsnc((String)map.get("DSNC"));
        zzf.setNum(Long.valueOf(map.get("NUM").toString()));
        return zzf;
    }
    public QqForm wxsmapToForm(Map map){
        QqForm zzf = new QqForm();
        zzf.setZhxx((String) map.get("ZHXX"));
        zzf.setZhnc((String) map.get("ZHNC"));
        zzf.setDszh((String)map.get("QUNZHXX"));
        zzf.setNum(Long.valueOf(map.get("NUM").toString()));
        return zzf;
    }
}
