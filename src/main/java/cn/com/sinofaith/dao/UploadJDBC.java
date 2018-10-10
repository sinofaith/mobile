package cn.com.sinofaith.dao;

import cn.com.sinofaith.bean.*;
import cn.com.sinofaith.util.TimeFormatUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadJDBC {
    //机主信息
    public static int insertJzxx(TAutoJzxxEntity tAutoJzxxEntity,Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_jzxx (Name,biem,Sex,zjhm,sjhm,mac," +
                "yhsbm,sbsbm,sjxh,gzdw,xzzqh,xzz,hjdqh,hjd,beizhu,zjlx,cjsj," +
                "sbbh,daorusj,gsd,data_type,aj_id)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, tAutoJzxxEntity.getName());
            pstmt.setString(2, tAutoJzxxEntity.getBiem());
            pstmt.setString(3, tAutoJzxxEntity.getSex());
            pstmt.setString(4, tAutoJzxxEntity.getZjhm());
            pstmt.setString(5, tAutoJzxxEntity.getSjhm());
            pstmt.setString(6, tAutoJzxxEntity.getMac());
            pstmt.setString(7, tAutoJzxxEntity.getYhsbm());
            pstmt.setString(8, tAutoJzxxEntity.getSbsbm());
            pstmt.setString(9, tAutoJzxxEntity.getSjxh());
            pstmt.setString(10, tAutoJzxxEntity.getGzdw());
            pstmt.setString(11, tAutoJzxxEntity.getXzzqh());
            pstmt.setString(12, tAutoJzxxEntity.getXzz());
            pstmt.setString(13, tAutoJzxxEntity.getHjdqh());
            pstmt.setString(14, tAutoJzxxEntity.getHjd());
            pstmt.setString(15, tAutoJzxxEntity.getBeizhu());
            pstmt.setString(16, tAutoJzxxEntity.getZjlx());
            pstmt.setString(17, tAutoJzxxEntity.getCjsj());
            pstmt.setString(18, tAutoJzxxEntity.getSbbh());
            pstmt.setString(19, tAutoJzxxEntity.getDaorusj());
            pstmt.setString(20, tAutoJzxxEntity.getGsd());
            pstmt.setString(21, tAutoJzxxEntity.getDataType());
            pstmt.setLong(22, tAutoJzxxEntity.getAj_id());
//            pstmt.setString(22, tAutoJzxxEntity.getInsertTime());
//            pstmt.setString(23, tAutoJzxxEntity.getIccid());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    通讯录
    public static int insertTxl(TAutoTxlEntity tAutoTxlEntity,Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_txl (u_name,u_number,p_name,p_number1,p_number2,data_type,aj_id)" +
                " values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, tAutoTxlEntity.getuName());
            pstmt.setString(2, tAutoTxlEntity.getuNumber());
            pstmt.setString(3, tAutoTxlEntity.getpName());
            pstmt.setString(4, tAutoTxlEntity.getpNumber1());
//            pstmt.setString(5, tAutoTxlEntity.getInsertTime());
            pstmt.setString(5, tAutoTxlEntity.getpNumber2());
            pstmt.setString(6, tAutoTxlEntity.getDataType());
            pstmt.setLong(7, tAutoTxlEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    通话清单
    public static int insertThqd(TAutoThqdEntity tAutoThqdEntity,Connection conn) {
//    Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_thqd (u_number,p_number,call_date,call_time,call_value,call_type," +
                "u_name,p_name,flg,data_type,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, tAutoThqdEntity.getuNumber());
            pstmt.setString(2, tAutoThqdEntity.getpNumber());
            pstmt.setString(3, tAutoThqdEntity.getCallDate());
            pstmt.setString(4, tAutoThqdEntity.getCallTime());
            pstmt.setString(5, tAutoThqdEntity.getCallValue());
            pstmt.setString(6, tAutoThqdEntity.getCallType());
            pstmt.setString(7, tAutoThqdEntity.getuName());
            pstmt.setString(8, tAutoThqdEntity.getpName());
//        pstmt.setString(10, tAutoThqdEntity.getInsertTime());
            pstmt.setString(9, tAutoThqdEntity.getFlg());
            pstmt.setString(10, tAutoThqdEntity.getDataType());
            pstmt.setLong(11, tAutoThqdEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    短信
    public static int insertDx(TAutoDxEntity tAutoDxEntity,Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_dx (u_name,u_number,p_name,p_number,content,send_time,send_type,data_type,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, tAutoDxEntity.getuName());
            pstmt.setString(2, tAutoDxEntity.getuNumber());
            pstmt.setString(3, tAutoDxEntity.getpName());
            pstmt.setString(4, tAutoDxEntity.getpNumber());
            pstmt.setString(5, tAutoDxEntity.getContent());
//            pstmt.setString(6, tAutoDxEntity.getInsertTime());
            pstmt.setString(6, tAutoDxEntity.getSendTime());
            pstmt.setString(7, tAutoDxEntity.getSendType());
            pstmt.setString(8, tAutoDxEntity.getDataType());
            pstmt.setLong(9, tAutoDxEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    QQ账户信息
    public static int insertQQ(TAutoQqZhxxEntity qqZhxxEntity,Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_qq_zhxx (name,sfzhm,sjhm,sex,qq, nicheng,glzh,age,szd,mima, birthday,gxqm,data_type,aj_id)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, qqZhxxEntity.getName());
            pstmt.setString(2, qqZhxxEntity.getSfzhm());
            pstmt.setString(3, qqZhxxEntity.getSjhm());
            pstmt.setString(4, qqZhxxEntity.getSex());
            pstmt.setString(5, qqZhxxEntity.getQq());
            pstmt.setString(6, qqZhxxEntity.getNicheng());
            pstmt.setString(7, qqZhxxEntity.getGlzh());
            pstmt.setString(8, qqZhxxEntity.getAge());
            pstmt.setString(9, qqZhxxEntity.getSzd());
            pstmt.setString(10, qqZhxxEntity.getMima());
            pstmt.setString(11, qqZhxxEntity.getBirthday());
            pstmt.setString(12, qqZhxxEntity.getGxqm());
            pstmt.setString(13, qqZhxxEntity.getDataType());
            pstmt.setLong(14, qqZhxxEntity.getAj_id());
//            pstmt.setString(8, qqZhxxEntity.getInsertTime());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    QQ好友信息
    public static int insertQQFriends(TAutoQqFriendsxxEntity qqFriendsxxEntity,Connection conn) {
//    Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_qq_friendsxx (Name,sfzhm,sjhm,sex,qq,fdqq," +
                "friendqqnc,friendqqsex,qqfriendbz,qqfriendgxqm,qqfriendqh,qqfriendqshf,data_type,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, qqFriendsxxEntity.getName());
            pstmt.setString(2, qqFriendsxxEntity.getSfzhm());
            pstmt.setString(3, qqFriendsxxEntity.getSjhm());
            pstmt.setString(4, qqFriendsxxEntity.getSex());
            pstmt.setString(5, qqFriendsxxEntity.getQq());
            pstmt.setString(6, qqFriendsxxEntity.getFdqq());
            pstmt.setString(7, qqFriendsxxEntity.getFriendqqnc());
            pstmt.setString(8, qqFriendsxxEntity.getFriendqqsex());
            pstmt.setString(9, qqFriendsxxEntity.getQqfriendbz());
            pstmt.setString(10, qqFriendsxxEntity.getQqfriendgxqm());
            pstmt.setString(11, qqFriendsxxEntity.getQqfriendqh());
            pstmt.setString(12, qqFriendsxxEntity.getQqfriendqshf());
            pstmt.setString(13,qqFriendsxxEntity.getDataType());
            pstmt.setLong(14,qqFriendsxxEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    微信账户信息
    public static int insertWechat(TAutoWechatZhxxEntity wechatZhxxEntity, Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_wechat_zhxx (name,sfzhm,sjhm,sex,wechatno, nicheng,qq,miyao,mysflx,email, sheng,shi,wxh,gxqm,data_type,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, wechatZhxxEntity.getName());
            pstmt.setString(2, wechatZhxxEntity.getSfzhm());
            pstmt.setString(3, wechatZhxxEntity.getSjhm());
            pstmt.setString(4, wechatZhxxEntity.getSex());
            pstmt.setString(5, wechatZhxxEntity.getWechatno());
            pstmt.setString(6, wechatZhxxEntity.getNicheng());
            pstmt.setString(7, wechatZhxxEntity.getQq());
            pstmt.setString(8, wechatZhxxEntity.getMiyao());
            pstmt.setString(9, wechatZhxxEntity.getMysflx());
            pstmt.setString(10, wechatZhxxEntity.getEmail());
            pstmt.setString(11, wechatZhxxEntity.getSheng());
            pstmt.setString(12, wechatZhxxEntity.getShi());
            pstmt.setString(13, wechatZhxxEntity.getWxh());
            pstmt.setString(14, wechatZhxxEntity.getGxqm());
            pstmt.setString(15, wechatZhxxEntity.getDataType());
            pstmt.setLong(16, wechatZhxxEntity.getAj_id());
//            pstmt.setString(9, wechatZhxxEntity.getInsertTime());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    微信好友信息
    public static int insertWechatFriends(TAutoWechatFriendsxxEntity wechatFriendsxxEntity, Connection conn) {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_wechat_friendsxx (Name,sfzhm,sjhm,sex,wechatno," +
                "fdwechatno,friendnc,friendsex,friendbz,friendqm, friendszd,friendqh,data_type,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, wechatFriendsxxEntity.getName());
            pstmt.setString(2, wechatFriendsxxEntity.getSfzhm());
            pstmt.setString(3, wechatFriendsxxEntity.getSjhm());
            pstmt.setString(4, wechatFriendsxxEntity.getSex());
            pstmt.setString(5, wechatFriendsxxEntity.getWechatno());
            pstmt.setString(6, wechatFriendsxxEntity.getFdwechatno());
            pstmt.setString(7, wechatFriendsxxEntity.getFriendnc());
            pstmt.setString(8, wechatFriendsxxEntity.getFriendsex());
//            pstmt.setString(10, wechatFriendsxxEntity.getInsertTime());
            pstmt.setString(9, wechatFriendsxxEntity.getFriendbz());
            pstmt.setString(10, wechatFriendsxxEntity.getFriendqm());
            pstmt.setString(11, wechatFriendsxxEntity.getFriendszd());
            pstmt.setString(12, wechatFriendsxxEntity.getFriendqh());
            pstmt.setString(13, wechatFriendsxxEntity.getDataType());
            pstmt.setLong(14, wechatFriendsxxEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static  int insertZjxx(TZjxxEntity zjxx, Connection con){
        int i = 0 ;
        String sql = "insert into t_zjxx(zjm,zjbb,inserttime) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setString(1, zjxx.getZjm());
            pstmt.setString(2, zjxx.getZjbb());
            pstmt.setString(3, TimeFormatUtil.getDate("/"));
            i = pstmt.executeUpdate();
            pstmt.close();
//        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //    QQ聊天记录
    public static int insertQQLtjl(TAutoQqLtjlEntity qqLtjlEntity, Connection conn) {
//    Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_qq_ltjl (fsqq,fsqqnc,fstime,jsqqno,jsqqnc," +
                "fslx,lujing,data_type,u_name,u_number,fanr,aj_id)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, qqLtjlEntity.getFsqq());
            pstmt.setString(2, qqLtjlEntity.getFsqqnc());
            pstmt.setString(3, qqLtjlEntity.getFstime());
            pstmt.setString(4, qqLtjlEntity.getJsqqno());
            pstmt.setString(5, qqLtjlEntity.getJsqqnc());
            pstmt.setString(6, qqLtjlEntity.getFslx());
            pstmt.setString(7, qqLtjlEntity.getLujing());
//        pstmt.setString(9, qqLtjlEntity.getInsertTime());
            pstmt.setString(8,qqLtjlEntity.getDataType());
            pstmt.setString(9, qqLtjlEntity.getuName());
            pstmt.setString(10, qqLtjlEntity.getuNumber());
            pstmt.setBytes(11, qqLtjlEntity.getFanr());
            pstmt.setLong(12, qqLtjlEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //    微信聊天记录
    public static int insertWechatLtjl(TAutoWechatLtjlEntity wechatLtjlEntity, Connection conn)  {
//        Connection conn = getConn();
        int i = 0;
        String sql = "insert into t_auto_wechat_ltjl (fswechatno,fswechatnc,fstime,jswechatno,jsfriendnc," +
                "fslx,lujing,data_type,u_name,u_number,fanr,aj_id)" +
                " values(?,?,?,?,?, ?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, wechatLtjlEntity.getFswechatno());
            pstmt.setString(2, wechatLtjlEntity.getFswechatnc());
            pstmt.setString(3, wechatLtjlEntity.getFstime());
            pstmt.setString(4, wechatLtjlEntity.getJswechatno());
            pstmt.setString(5, wechatLtjlEntity.getJsfriendnc());
            pstmt.setString(6, wechatLtjlEntity.getFslx());
            pstmt.setString(7, wechatLtjlEntity.getLujing());
//            pstmt.setString(9, wechatLtjlEntity.getInsertTime());
            pstmt.setString(8, wechatLtjlEntity.getDataType());
            pstmt.setString(9, wechatLtjlEntity.getuName());
            pstmt.setString(10, wechatLtjlEntity.getuNumber());
            pstmt.setBytes(11,wechatLtjlEntity.getFanr());
            pstmt.setLong(12,wechatLtjlEntity.getAj_id());
            i = pstmt.executeUpdate();
            pstmt.close();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
