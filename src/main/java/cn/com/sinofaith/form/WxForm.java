package cn.com.sinofaith.form;

import java.util.Map;

/**
 * 微信聊天数据
 */
public class WxForm {
    private String rn;
    // 姓名
    private String nickname;

    private String fstime;

    private String label;

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFstime() {
        return fstime;
    }

    public void setFstime(String fstime) {
        this.fstime = fstime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public WxForm wxsmapToForm(Map map){
        WxForm zzf = new WxForm();
        zzf.setRn( map.get("RN").toString());
        if("发送".equals((String) map.get("FSFX"))){
            zzf.setNickname((String) map.get("ZHNC"));
        }else{
            zzf.setNickname((String) map.get("DSNC"));
        }
        zzf.setFstime((String)map.get("FSTIME"));
        zzf.setLabel((String)map.get("LUJING"));
        return zzf;
    }
}
