package cn.com.sinofaith.util;



import cn.com.sinofaith.bean.RoleEntity;

import java.text.SimpleDateFormat;

/**
 * @author Created by Me. on 2018/5/22
 */
public class TimeFormatUtil {

    public static String getDate(String style){
        String time = "";
        if("/".equals(style)){
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            time = df.format(System.currentTimeMillis());
        }else if("-".equals(style)){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = df.format(System.currentTimeMillis());
        }else if("".equals(style)){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            time = df.format(System.currentTimeMillis());
        }else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            time = df.format(System.currentTimeMillis());
        }
        return time;
    }

    public static void main(String[] args) {

        RoleEntity e = new RoleEntity();
        String [] a = e.toString().split(",");
        for(int i =0 ; i<a.length;i++){
            System.out.println(a[i]);
        }

//        String a = "http://15.6.13.9:90//datacache///019154//groupmonitor//20181108/v11gvOc0Lyq0UbjZwISlxNDGSl8avLK142JNubOmTveInkcvB01ZpwaOdyvCzokhT.jpg";
//
//        System.out.println(a.substring(a.lastIndexOf("/"),a.length()));
    }
}