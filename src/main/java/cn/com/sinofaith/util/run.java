package cn.com.sinofaith.util;

import cn.com.sinofaith.bean.*;
import cn.com.sinofaith.service.UploadServices;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.com.sinofaith.dao.UploadJDBC.*;

public class run {
    private static final String FILEPATH;

    static {

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

        FILEPATH = bundle.getString("jdbc.path");
    }
    public static void main(String[] args) {
        try {
            String filePath = "D:\\work\\html\\案件（20190508172909）_20190509191832_html\\Report\\";//report 路径 D:\\取证报告\\陈飞\\Report\\
            String name = "";//机主名
            String sjly = ""; // 数据来源



            FileOutputStream fs = new FileOutputStream(new File("jSoupTextResult.txt"));
            PrintStream p = new PrintStream(fs);
            File input = new File(filePath + "Catalog.html");
            System.out.println("开始读入" + filePath + "Catalog.html");
            Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements scripts = document.getElementsByTag("script");
            String s;
            RoleEntity roleEntity = new RoleEntity();

            // 设置一个添加人员对象
            roleEntity.setInsertTime(TimeFormatUtil.getDate("/"));
            TAutoJzxxEntity jzxx = new TAutoJzxxEntity();
            TAutoTxlEntity txl = new TAutoTxlEntity();
            TAutoDxEntity dx = new TAutoDxEntity();
            TAutoThqdEntity thdq = new TAutoThqdEntity();
            TAutoQqZhxxEntity qqZhxxEntity = new TAutoQqZhxxEntity();
            TAutoWechatZhxxEntity wechatZhxxEntity = new TAutoWechatZhxxEntity();
            TAutoQqFriendsxxEntity qqFriendsxxEntity = new TAutoQqFriendsxxEntity();
            TAutoWechatFriendsxxEntity wechatFriendsxxEntity = new TAutoWechatFriendsxxEntity();
            TAutoQqLtjlEntity qqLtjlEntity = new TAutoQqLtjlEntity();
            TAutoWechatLtjlEntity wechatLtjlEntity = new TAutoWechatLtjlEntity();

            Connection conn = DBUtil.getConnection();
//            Connection conn = null;
    //
            jzxx.setName(name);
            jzxx.setDataType(sjly);
            dx.setDataType(sjly);
            qqFriendsxxEntity.setDataType(sjly);
            qqLtjlEntity.setDataType(sjly);
            qqZhxxEntity.setDataType(sjly);
            thdq.setDataType(sjly);
            txl.setDataType(sjly);
            wechatFriendsxxEntity.setDataType(sjly);
            wechatLtjlEntity.setDataType(sjly);
            wechatZhxxEntity.setDataType(sjly);

    //                System.out.println(jzxx.getName());
    //        在脚本文件寻找dtree
            for (Element script : scripts) {
    //            System.out.println("!!!");
                s = script.data();
                String a[] = s.split("\\n");
                for (String ai : a) {
                    jzxxParser(ai, jzxx,roleEntity, filePath, p, conn, name);
                    txlParser(ai, jzxx, txl, filePath, p, conn);
                    dxParser(ai, jzxx, dx, filePath, p, conn);
                    thqdParser(ai, jzxx, thdq, filePath, p, conn);
                    qqWechatParser(ai, jzxx, qqZhxxEntity, wechatZhxxEntity, filePath, p, conn);
                    qqFriendsParser(ai, jzxx, qqZhxxEntity, qqFriendsxxEntity, filePath, p, conn);
                    wechatFriendsParser(ai, jzxx, wechatFriendsxxEntity, wechatZhxxEntity, filePath, p, conn);
                    qqWechatLtjlParser(ai,jzxx, qqLtjlEntity, wechatLtjlEntity,qqZhxxEntity,wechatZhxxEntity, filePath, p, conn);

                }
            }
        }catch (FileNotFoundException e ){
            System.out.println("找不到Report");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String jumpPage(String info, String ai) {
        if (ai.contains(info)) {
            String a[] = ai.split("'");
            System.out.println(ai);
//            System.out.println("~~~~~~~~~~~");
            return a[3];
        }
        return "";
    }

    public static void qqWechatLtjlParser(String ai, TAutoJzxxEntity jzxxEntity,TAutoQqLtjlEntity qqLtjlEntity,
                                          TAutoWechatLtjlEntity wechatLtjlEntity,TAutoQqZhxxEntity qqzh,TAutoWechatZhxxEntity wezh,
                                          String filePath, PrintStream p,Connection conn){
//                过滤掉qq
        if ((ai.contains("849_50900~50999.ico")||ai.contains("27_301~349.ico"))&&(ai.contains("好友聊天记录")||ai.contains("群聊天"))) {
//                查找基本信息字符,返回跳转文件及位置（扩展时候在这加一个找的内容的数组，循环查找遍历）
//                    先读普通好友列表 再读群成员列表
//            message(p,ai);
            String herf[] = jumpPage("聊天", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件

                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = null;
                try {
                    documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("正在读取" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  微信好友信息不加
                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                message(p, beforeElementTables.toString());

//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
                String afterElementTables[] = herf[1].split("\\.");
                int afteri[] = new int[afterElementTables.length];
                String afterElementTable[] = new String[afterElementTables.length];
                for (int e = 0; e < afterElementTables.length; e++) {
                    afteri[e] = Integer.parseInt(afterElementTables[e]);

                    afterElementTable[e] = String.valueOf(afteri[e]);//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
                }
//                定位后找其后面要输入数据库的数据的表格
                if (beforeElementTables != null) {
                    for (Element w : beforeElementTables) {
//                            表格元素
                        String qun="";//群号

                        w = w.parent().parent().parent().parent().parent().nextElementSibling();
                        qun = w.text().substring(0,w.text().lastIndexOf("("));
                        Element elementTable = w.nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");
                        int flg = 0;
                        int flgqun=0;//群标记
                        while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                            if(elementTable.select("table").attr("class").equals("OuterTableList")){
                                Elements trs = elementTable.select("table.OuterTableList > tbody > tr");
                                for(Element tr:trs){
                                    wechatLtjlEntity.setNull();
                                    qqLtjlEntity.setNull();
                                    qqLtjlEntity.setZhxx(qqzh.getQq());
                                    qqLtjlEntity.setZhnc(qqzh.getNicheng());
                                    wechatLtjlEntity.setZhxx(wezh.getWxh());
                                    wechatLtjlEntity.setZhnc(wezh.getNicheng());
                                    Elements tds =new Elements();
                                    String content  = "";
                                    String lx ="";
                                    String dszh = "";
                                    String dsnc = "";
                                    String fstime = "";
                                    if(tr.hasClass("tableheader")){
                                        if(tr.text().contains("接收")){
                                            flgqun = 0;
                                        }else{
                                            flgqun = 1;
                                        }
                                    }else{
                                        String[] fsf = tr.child(1).text().split("\\(|\\)");
                                        String[] jsf = tr.child(2).text().split("\\(|\\)");
                                        if(fsf.length<1||fsf==null){
                                            continue;
                                        }
                                        if(ai.contains("27_301~349.ico")){
                                            //qq聊天
                                            if(flgqun==0){
                                                if(qqLtjlEntity.getZhxx().equals(fsf[0])){
                                                    dszh = jsf[0];
                                                    if(jsf.length>1) {
                                                        dsnc = jsf[1];
                                                    }
                                                    qqLtjlEntity.setFsfx("发送");
                                                }else{
                                                    dszh = fsf[0];
                                                    if(fsf.length>1) {
                                                        dsnc = fsf[1];
                                                    }
                                                    qqLtjlEntity.setFsfx("接收");
                                                }
                                                fstime = tr.child(3).text();
                                                    Element e = tr.child(4);
                                                tds = e.select("table > tbody > tr > td:eq(1)");
                                            }else{
                                                if(qqLtjlEntity.getZhxx().equals(fsf[0])){

                                                }else{
                                                    dszh = fsf[0];
                                                    if (fsf.length>1) {
                                                        dsnc = fsf[1];
                                                    }
                                                    qqLtjlEntity.setFsfx("接收");
                                                }
                                                fstime = tr.child(2).text();
                                                Element e = tr.child(3);
                                                tds = e.select("table > tbody > tr > td:eq(1)");
                                            }
                                            if(tds.size()>0){
                                                if(tds.size()==3){
                                                    content = tds.get(0).text();
                                                    lx = tds.get(1).text();
                                                }
                                                if(tds.size()==4){
                                                    if(tds.select("quote > a").size()==1){
                                                        content = tds.select("quote > a").get(0).attr("href");
                                                        if(content.contains(".")) {
                                                            lx = content.substring(content.lastIndexOf("."));
                                                        }else {
                                                            lx = "文件";
                                                        }
                                                    }else{
                                                        content = tds.select("quote").text();
                                                        lx = "文本";
                                                    }
                                                }
                                            }
                                            qqLtjlEntity.setLujing(content);
                                            if(content.length()>100){
                                                qqLtjlEntity.setLujing(content.substring(0,100));
                                            }
                                            qqLtjlEntity.setDsnc(dsnc);
                                            qqLtjlEntity.setDszh(dszh);
                                            qqLtjlEntity.setFstime(fstime);
                                            qqLtjlEntity.setFslx(lx);
                                            System.out.println(qqLtjlEntity);
                                            if(qqLtjlEntity.getDszh()!=null) {
                                                insertQQLtjl(qqLtjlEntity, conn);
                                            }
                                        }else{
                                            if(flgqun==0){
                                                //微信好友聊天
                                                if(wechatLtjlEntity.getZhxx().equals(fsf[0])){
                                                    dszh = jsf[0];
                                                    if(jsf.length>1) {
                                                        dsnc = jsf[1];
                                                    }
                                                    wechatLtjlEntity.setFsfx("发送");
                                                }else{
                                                    dszh = fsf[0];
                                                    if(fsf.length>1) {
                                                        dsnc = fsf[1];
                                                    }
                                                    wechatLtjlEntity.setFsfx("接收");
                                                }
                                                fstime = tr.child(3).text();
                                                Element e = tr.child(4);
                                                tds = e.select("table > tbody > tr > td:eq(1)");

                                            }else{
                                                //微信群聊天
                                                if(wechatLtjlEntity.getZhxx().equals(fsf[0])){
                                                    //dszh = jsf[0];
                                                    //dsnc = jsf[1];
                                                    //wechatLtjlEntity.setFsfx("发送");
                                                }else{
                                                    dszh = fsf[0];
                                                    if(fsf.length>1) {
                                                        dsnc = fsf[1];
                                                    }
                                                    wechatLtjlEntity.setFsfx("接收");
                                                }
                                                fstime = tr.child(2).text();
                                                Element e = tr.child(3);
                                                tds = e.select("table > tbody > tr > td:eq(1)");
                                            }
                                            if(tds.size()>0){
                                                if(tds.size()==4){
                                                    content = tds.get(0).text();
                                                    lx = tds.get(1).text();
                                                }
                                                if(tds.size()==5){
                                                    if(tds.select("quote > a").size()==1){
                                                        content = tds.select("quote > a").get(0).attr("href");
                                                        if(content.contains(".")) {
                                                            lx = content.substring(content.lastIndexOf("."));
                                                        }else {
                                                            lx = "图片文件";
                                                        }
                                                    }else{
                                                        content = tds.select("quote").text();
                                                        lx = "文本";
                                                    }
                                                }
                                            }

                                            wechatLtjlEntity.setLujing(content);
                                            if(content.length()>100){
                                                wechatLtjlEntity.setLujing(content.substring(0,100));
                                            }
                                            wechatLtjlEntity.setFslx(lx);
                                            wechatLtjlEntity.setDszh(dszh);
                                            wechatLtjlEntity.setDsnc(dsnc);
                                            wechatLtjlEntity.setFstime(fstime);
                                            if(wechatLtjlEntity.getDszh()!=null) {
                                                insertWechatLtjl(wechatLtjlEntity, conn);
                                            }
                                        }
                                    }
                                }
                            }
                            if (elementTable.select("table").attr("class").equals("OuterTable")) {
                                Elements trHeads = elementTable.getElementsByClass("tableheader");
                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element trHead : trHeads) {
//                                                message(p,"Head~~~~~~~~~~~"+trHead.toString());
//                                                过滤掉群聊天
                                    if (trHead.text().contains("接收")) {
                                        flgqun=0;
                                        //微信好友列表
//                                        因为聊天记录里面也包含好友列表字符 通过表头过滤一下
                                        for (Element tr : trs) {
                                            wechatLtjlEntity.setNull();
                                            qqLtjlEntity.setNull();
                                            qqLtjlEntity.setZhxx(qqzh.getQq());
                                            qqLtjlEntity.setZhnc(qqzh.getNicheng());
                                            wechatLtjlEntity.setZhxx(wezh.getWxh());
                                            wechatLtjlEntity.setZhnc(wezh.getNicheng());
//                                            message(p, "非群~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 6;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        String fsf[]=td.text().split("\\(|\\)");
                                                        if(fsf.length>1){
//                                                            message(p,td.text()+"\t"+fsf[0]+"\t"+fsf[1]);
                                                            if (ai.contains("27_301~349.ico")){
                                                                if(qqLtjlEntity.getZhxx().equals(fsf[0])){
                                                                    qqLtjlEntity.setFsfx("发送");
                                                                }else {
                                                                    qqLtjlEntity.setFsfx("接收");
                                                                    qqLtjlEntity.setDszh(fsf[0]);
                                                                    qqLtjlEntity.setDsnc(fsf[1]);
                                                                }
//                                                                qqLtjlEntity.setFsqq(fsf[0]);
//                                                                qqLtjlEntity.setFsqqnc(fsf[1]);
                                                            }else {
                                                                if(wechatLtjlEntity.getZhxx().equals(fsf[0])){
                                                                    wechatLtjlEntity.setFsfx("发送");
                                                                }else {
                                                                    wechatLtjlEntity.setFsfx("接收");
                                                                    wechatLtjlEntity.setDszh(fsf[0]);
                                                                    wechatLtjlEntity.setDsnc(fsf[1]);
                                                                }
//                                                                wechatLtjlEntity.setFswechatno(fsf[0]);
//                                                                wechatLtjlEntity.setFswechatnc(fsf[1]);
                                                            }
                                                        }else {
                                                            if (ai.contains("27_301~349.ico")){
//                                                                qqLtjlEntity.setFsqq(td.text());
                                                                qqLtjlEntity.setDszh(td.text());
                                                                qqLtjlEntity.setFsfx("");
                                                            }else {
//                                                                wechatLtjlEntity.setFswechatno(td.text());
                                                                wechatLtjlEntity.setDszh(td.text());
                                                                wechatLtjlEntity.setFsfx("");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case 3: {
                                                        String jsf[]=td.text().split("\\(|\\)");
                                                        if(jsf.length>1){
//                                                            message(p,td.text()+"\t"+jsf[0]+"\t"+jsf[1]);
                                                            if (ai.contains("27_301~349.ico")){
                                                                if(qqLtjlEntity.getZhxx().equals(jsf[0])){
                                                                    qqLtjlEntity.setFsfx("接收");
                                                                }else{
                                                                    qqLtjlEntity.setFsfx("发送");
                                                                    qqLtjlEntity.setDszh(jsf[0]);
                                                                    qqLtjlEntity.setDsnc(jsf[1]);
                                                                }
//                                                                qqLtjlEntity.setJsqqno(jsf[0]);
//                                                                qqLtjlEntity.setJsqqnc(jsf[1]);
                                                            }else {
                                                                if(wechatLtjlEntity.getZhxx().equals(jsf[0])){
                                                                    wechatLtjlEntity.setFsfx("接收");
                                                                }else {
                                                                    wechatLtjlEntity.setFsfx("发送");
                                                                    wechatLtjlEntity.setDszh(jsf[0]);
                                                                    wechatLtjlEntity.setDsnc(jsf[1]);
                                                                }
//                                                                wechatLtjlEntity.setJswechatno(jsf[0]);
//                                                                wechatLtjlEntity.setJsfriendnc(jsf[1]);
                                                            }
                                                        }else {
                                                            if (ai.contains("27_301~349.ico")){
//                                                                qqLtjlEntity.setJsqqno(td.text());
                                                                qqLtjlEntity.setDszh(td.text());
                                                                qqLtjlEntity.setFsfx("");
                                                            }else {
//                                                                wechatLtjlEntity.setJswechatno(td.text());
                                                                wechatLtjlEntity.setDszh(td.text());
                                                                wechatLtjlEntity.setFsfx("");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case 4: {
                                                        if (ai.contains("27_301~349.ico")){
                                                            qqLtjlEntity.setFstime(td.text());
                                                        }else {
                                                            wechatLtjlEntity.setFstime(td.text());
                                                        }
                                                        break;
                                                    }
                                                    case 5: {
//                                                                    message(p,"blob~~~~~~~~~~~");
                                                        Elements quotes=td.getElementsByTag("a");
//                                                                    message(p,quotes.size()+"");

                                                        if(quotes.select("img").size()==0) {
//                                                                        message(p,"null");
                                                            if (ai.contains("27_301~349.ico")) {
                                                                qqLtjlEntity.setFslx("文字");
                                                                try {
                                                                    if (td.text().length() > 100) {
                                                                        String wz = td.text().substring(0, 100);
//                                                                        qqLtjlEntity.setFanr(wz.getBytes("UTF8"));
                                                                        qqLtjlEntity.setLujing(wz);
//                                                                    message(p,"qq文字"+new String(qqLtjlEntity.getFanr(),"UTF8"));
                                                                    } else {
//                                                                        qqLtjlEntity.setFanr(td.text().getBytes("UTF8"));
                                                                        qqLtjlEntity.setLujing(td.text());
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            } else {
                                                                wechatLtjlEntity.setFslx("文字");
                                                                try {
                                                                    if(td.text().length()>100) {
                                                                        String wz = td.text().substring(0, 100);
//                                                                        wechatLtjlEntity.setFanr(wz.getBytes("UTF8"));
                                                                        wechatLtjlEntity.setLujing(wz);

//                                                                message(p,"微信文字"+new String(wechatLtjlEntity.getFanr(),"UTF8"));
                                                                    }else{
//                                                                        wechatLtjlEntity.setFanr(td.text().getBytes("UTF8"));
                                                                        wechatLtjlEntity.setLujing(td.text());
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }else if(!quotes.text().contains("好友验证")){
                                                            String fslx[];
                                                            String lujing;
                                                            for (Element quote:quotes
                                                                    ) {
//                                                                            message(p,"notnull"+quote.text());
                                                                lujing=quote.attr("href");
                                                                fslx=quote.attr("href").split("\\.");
                                                                if (ai.contains("27_301~349.ico")){
                                                                    if(fslx.length>1){
                                                                        qqLtjlEntity.setFslx(fslx[fslx.length-1].replace("amr","mp3"));
//                                                                        message(p,qqLtjlEntity.getFslx());
                                                                    }
                                                                    qqLtjlEntity.setLujing("E:"+File.separator+"BLOB文件路径"+File.separator+jzxxEntity.getName()+File.separator+lujing);
                                                                }else {
                                                                    if(fslx.length>1){
                                                                        wechatLtjlEntity.setFslx(fslx[fslx.length-1].replace("amr","mp3"));
//                                                                        message(p,wechatLtjlEntity.getFslx());
                                                                    }
                                                                    wechatLtjlEntity.setLujing("E:"+File.separator+"BLOB文件路径"+File.separator+jzxxEntity.getName()+File.separator+lujing);
                                                                }
                                                                String sblob=filePath+lujing;
                                                                String endPath= FILEPATH+jzxxEntity.getName()+jzxxEntity.getInsertTime().replace(":","").replace("-","").replace(" ","");
                                                                File file = new File(sblob);
                                                                OutputStream os =null;
                                                                try {
                                                                    if(file.exists()&&file.isFile()) {
                                                                        //新建一byte数组
//                                                                        byte[] buf = new byte[inputStream.available()];
                                                                        //将文件读入到byte[]中
//                                                                        inputStream.read(buf);
                                                                        if (ai.contains("27_301~349.ico")) {
                                                                            File endFile = new File(endPath+File.separator+"qq");
                                                                            if(!endFile.exists()){
                                                                                endFile.mkdirs();
                                                                            }
                                                                            if(file.getName().endsWith(".amr")){
                                                                                Amr2Mp3.changeToMp3(file.getAbsolutePath(),endFile.getAbsolutePath() + File.separator + file.getName().replace(".amr",".mp3"));
                                                                                qqLtjlEntity.setLujing(endFile + File.separator + file.getName().replace(".amr",".mp3"));
                                                                            }else {
                                                                                os = new FileOutputStream(endFile.getAbsolutePath() + File.separator + file.getName());
                                                                                FileUtils.copyFile(file, os);
                                                                                qqLtjlEntity.setLujing(endFile+File.separator+ file.getName());
                                                                                os.close();
                                                                            }
//                                                                            qqLtjlEntity.setFanr(buf);
                                                                        } else {
                                                                            File endFile = new File(endPath+File.separator+"wechat");
                                                                            if(!endFile.exists()){
                                                                                endFile.mkdirs();
                                                                            }
                                                                            if(file.getName().endsWith(".amr")){
                                                                                Amr2Mp3.changeToMp3(file.getAbsolutePath(),endFile.getAbsolutePath() + File.separator + file.getName().replace(".amr",".mp3"));
                                                                                wechatLtjlEntity.setLujing(endFile + File.separator + file.getName().replace(".amr",".mp3"));
                                                                            }else {
                                                                                os = new FileOutputStream(endFile.getAbsolutePath() + File.separator + file.getName());
                                                                                FileUtils.copyFile(file, os);
                                                                                wechatLtjlEntity.setLujing(endFile + File.separator + file.getName());
//                                                                            wechatLtjlEntity.setFanr(buf);
                                                                                os.close();
                                                                            }
//                                                                            wechatLtjlEntity.setFanr(buf);
                                                                        }
                                                                    }else{
                                                                        if (ai.contains("27_301~349.ico")) {
                                                                            qqLtjlEntity.setFslx("文字");
                                                                            qqLtjlEntity.setLujing(file.getName()+"-取证报告内该文件丢失");
                                                                        }else{
                                                                            wechatLtjlEntity.setFslx("文字");
                                                                            wechatLtjlEntity.setLujing(file.getName()+"-取证报告内该文件丢失");
                                                                        }
                                                                    }
                                                                } catch (FileNotFoundException e) {
                                                                    e.printStackTrace();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }

                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 5) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                            message(p, td.toString());
                                            }
                                            if (ai.contains("27_301~349.ico")){
                                                if(qqLtjlEntity.getDszh()!=null){
                                                    qqLtjlEntity.setuName(jzxxEntity.getName());
                                                    qqLtjlEntity.setuNumber(jzxxEntity.getSjhm());
                                                    qqLtjlEntity.setAj_id(jzxxEntity.getAj_id());
                                                    qqLtjlEntity.setQunzhxx("");
                                                    insertQQLtjl(qqLtjlEntity,conn);
                                                    System.out.println(qqLtjlEntity);
                                                }
//                                                    message(p,insertQQLtjl(qqLtjlEntity,conn)+"qq");
                                            }else {
                                                if(wechatLtjlEntity.getDszh()!=null){
                                                    wechatLtjlEntity.setuName(jzxxEntity.getName());
                                                    wechatLtjlEntity.setuNumber(jzxxEntity.getSjhm());
                                                    wechatLtjlEntity.setAj_id(jzxxEntity.getAj_id());
                                                    wechatLtjlEntity.setQunzhxx("");
                                                    insertWechatLtjl(wechatLtjlEntity,conn);
                                                    System.out.println(wechatLtjlEntity);
                                                }
//                                                    message(p,insertWechatLtjl(wechatLtjlEntity,conn)+"wx");
                                            }
                                        }//if(trHead.text().contains("发送"))
                                    } else if(trHead.text().contains("发送")){
                                        flgqun=1;
                                        for (Element tr : trs) {
                                            wechatLtjlEntity.setNull();
                                            qqLtjlEntity.setNull();
                                            qqLtjlEntity.setZhxx(qqzh.getQq());
                                            qqLtjlEntity.setZhnc(qqzh.getNicheng());
                                            wechatLtjlEntity.setZhxx(wezh.getWxh());
                                            wechatLtjlEntity.setZhnc(wezh.getNicheng());
//                                            message(p, "群~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,10}")) {
                                                            i = 5;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        String fsf[]=td.text().split("\\(|\\)");
                                                        if(fsf.length>1){
//                                                            message(p,td.text()+"\t"+fsf[0]+"\t"+fsf[1]);
                                                            if (ai.contains("27_301~349.ico")){
                                                                if(qqLtjlEntity.getZhxx().equals(fsf[0])){
                                                                    qqLtjlEntity.setFsfx("发送");
                                                                    qqLtjlEntity.setQunzhxx(qun);
                                                                }else{
                                                                    qqLtjlEntity.setFsfx("接收");
                                                                    qqLtjlEntity.setQunzhxx(qun);
                                                                }
                                                                qqLtjlEntity.setDszh(fsf[0]);
                                                                qqLtjlEntity.setDsnc(fsf[1]);
//                                                                qqLtjlEntity.setFsqq(fsf[0]);
//                                                                qqLtjlEntity.setFsqqnc(fsf[1]);
                                                            }else {
                                                                if(wechatLtjlEntity.getZhxx().equals(fsf[0])){
                                                                    wechatLtjlEntity.setDszh("发送");
                                                                    wechatLtjlEntity.setQunzhxx(qun);
                                                                }{
                                                                    wechatLtjlEntity.setFsfx("接收");
                                                                    wechatLtjlEntity.setQunzhxx(qun);
                                                                }
                                                                wechatLtjlEntity.setDszh(fsf[0]);
                                                                wechatLtjlEntity.setDsnc(fsf[1]);
//                                                                wechatLtjlEntity.setFswechatno(fsf[0]);
//                                                                wechatLtjlEntity.setFswechatnc(fsf[1]);
                                                            }
                                                        }else {
                                                            if (ai.contains("27_301~349.ico")){
//                                                                qqLtjlEntity.setFsqq(td.text().replace("()",""));
                                                                qqLtjlEntity.setDszh(td.text().replace("()",""));
                                                            }else {
//                                                                wechatLtjlEntity.setFswechatno(td.text().replace("()",""));
                                                                wechatLtjlEntity.setDszh(td.text().replace("()",""));
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case 3: {
                                                        if (ai.contains("27_301~349.ico")){
                                                            qqLtjlEntity.setFstime(td.text());
//                                                            message(p,qqLtjlEntity.getFstime());
                                                        }else {
                                                            wechatLtjlEntity.setFstime(td.text());
//                                                            message(p,wechatLtjlEntity.getFstime());
                                                        }
                                                        break;
                                                    }
                                                    case 4: {
//                                                                    message(p,"blob~~~~~~~~~~~");
                                                        Elements quotes=td.getElementsByTag("a");
//                                                                    message(p,quotes.size()+"");

                                                        if(quotes.select("img").size()==0) {
//                                                                        message(p,"null");
                                                            if (ai.contains("27_301~349.ico")) {
                                                                qqLtjlEntity.setFslx("文字");
                                                                try {
                                                                    if (td.text().length() > 100) {
                                                                        String wz = td.text().substring(0, 100);
//                                                                        qqLtjlEntity.setFanr(wz.getBytes("UTF8"));
                                                                        qqLtjlEntity.setLujing(wz);
//                                                                    message(p,"qq文字"+new String(qqLtjlEntity.getFanr(),"UTF8"));
                                                                    } else {
//                                                                        qqLtjlEntity.setFanr(td.text().getBytes("UTF8"));
                                                                        qqLtjlEntity.setLujing(td.text());
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            } else {
                                                                wechatLtjlEntity.setFslx("文字");
                                                                try {
                                                                    if(td.text().length()>100) {
                                                                        String wz = td.text().substring(0, 100);
//                                                                        wechatLtjlEntity.setFanr(wz.getBytes("UTF8"));
                                                                        wechatLtjlEntity.setLujing(wz);

//                                                                message(p,"微信文字"+new String(wechatLtjlEntity.getFanr(),"UTF8"));
                                                                    }else{
//                                                                        wechatLtjlEntity.setFanr(td.text().getBytes("UTF8"));
                                                                        wechatLtjlEntity.setLujing(td.text());
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }else if(!quotes.text().contains("好友验证")){
                                                            String fslx[];
                                                            String lujing;
                                                            for (Element quote:quotes
                                                                    ) {
//                                                                            message(p,"notnull"+quote.text());
                                                                lujing=quote.attr("href");
                                                                fslx=quote.attr("href").split("\\.");
                                                                if (ai.contains("27_301~349.ico")){
                                                                    if(fslx.length>1){
                                                                        qqLtjlEntity.setFslx(fslx[fslx.length-1].replace("amr","mp3"));
//                                                                        message(p,qqLtjlEntity.getFslx());
                                                                    }
                                                                    qqLtjlEntity.setLujing("E:"+File.separator+"BLOB文件路径"+File.separator+jzxxEntity.getName()+File.separator+lujing);
                                                                }else {
                                                                    if(fslx.length>1){
                                                                        wechatLtjlEntity.setFslx(fslx[fslx.length-1].replace("amr","mp3"));
//                                                                        message(p,wechatLtjlEntity.getFslx());
                                                                    }
                                                                    wechatLtjlEntity.setLujing("E:"+File.separator+"BLOB文件路径"+File.separator+jzxxEntity.getName()+File.separator+lujing);
                                                                }
                                                                String sblob=filePath+lujing;
                                                                String endPath= FILEPATH+jzxxEntity.getName()+jzxxEntity.getInsertTime().replace(":","").replace("-","").replace(" ","");
                                                                File file = new File(sblob);
                                                                OutputStream os =null;
                                                                try {
                                                                    if(file.exists()&&file.isFile()) {
                                                                        //新建一byte数组
//                                                                        byte[] buf = new byte[inputStream.available()];
                                                                        //将文件读入到byte[]中
//                                                                        inputStream.read(buf);
                                                                        if (ai.contains("27_301~349.ico")) {
                                                                            File endFile = new File(endPath+File.separator+"qq");
                                                                            if(!endFile.exists()){
                                                                                endFile.mkdirs();
                                                                            }
                                                                            if(file.getName().endsWith(".amr")){
                                                                                Amr2Mp3.changeToMp3(file.getAbsolutePath(),endFile.getAbsolutePath() + File.separator + file.getName().replace(".amr",".mp3"));
                                                                                qqLtjlEntity.setLujing(endFile + File.separator + file.getName().replace(".amr",".mp3"));
                                                                            }else {
                                                                                os = new FileOutputStream(endFile.getAbsolutePath() + File.separator + file.getName());
                                                                                FileUtils.copyFile(file, os);
                                                                                qqLtjlEntity.setLujing(endFile+ File.separator + file.getName());
                                                                                os.close();
                                                                            }
//                                                                            qqLtjlEntity.setFanr(buf);
                                                                        } else {
                                                                            File endFile = new File(endPath+File.separator+"wechat");
                                                                            if(!endFile.exists()){
                                                                                endFile.mkdirs();
                                                                            }
                                                                            if(file.getName().endsWith(".amr")){
                                                                                Amr2Mp3.changeToMp3(file.getAbsolutePath(),endFile.getAbsolutePath() + File.separator + file.getName().replace(".amr",".mp3"));
                                                                                wechatLtjlEntity.setLujing(endFile + File.separator + file.getName().replace(".amr",".mp3"));
                                                                            }else {
                                                                                os = new FileOutputStream(endFile.getAbsolutePath() + File.separator + file.getName());
                                                                                FileUtils.copyFile(file, os);
                                                                                wechatLtjlEntity.setLujing(endFile + File.separator + file.getName());
//                                                                            wechatLtjlEntity.setFanr(buf);
                                                                                os.close();
                                                                            }
//                                                                            wechatLtjlEntity.setFanr(buf);
                                                                        }
                                                                    }else{
                                                                        if (ai.contains("27_301~349.ico")) {
                                                                            qqLtjlEntity.setFslx("文字");
                                                                            qqLtjlEntity.setLujing(file.getName()+"-取证报告内该文件丢失");
                                                                        }else{
                                                                            wechatLtjlEntity.setFslx("文字");
                                                                            wechatLtjlEntity.setLujing(file.getName()+"-取证报告内该文件丢失");
                                                                        }
                                                                    }
                                                                } catch (FileNotFoundException e) {
                                                                    e.printStackTrace();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }

                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 4) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                            message(p, td.toString());
                                            }
//                                            message(p,wechatLtjlEntity.getFswechatno());
                                            if (ai.contains("27_301~349.ico")){
                                                if(qqLtjlEntity.getDszh()!=null) {
                                                    qqLtjlEntity.setQunzhxx(qun);
//                                                    qqLtjlEntity.setJsqqno(qun);
                                                    qqLtjlEntity.setuName(jzxxEntity.getName());
                                                    qqLtjlEntity.setuNumber(jzxxEntity.getSjhm());
                                                    qqLtjlEntity.setAj_id(jzxxEntity.getAj_id());
                                                    insertQQLtjl(qqLtjlEntity,conn);
                                                    System.out.println(qqLtjlEntity);
//                                                    message(p,insertQQLtjl(qqLtjlEntity,conn)+"qq");
//                                                    message(p,+"qq");
                                                }
                                            }else {
                                                if(wechatLtjlEntity.getDszh()!=null) {
                                                    wechatLtjlEntity.setQunzhxx(qun);
//                                                    wechatLtjlEntity.setJswechatno(qun);
                                                    wechatLtjlEntity.setuName(jzxxEntity.getName());
                                                    wechatLtjlEntity.setuNumber(jzxxEntity.getSjhm());
                                                    wechatLtjlEntity.setAj_id(jzxxEntity.getAj_id());
                                                    insertWechatLtjl(wechatLtjlEntity,conn);
                                                    System.out.println(wechatLtjlEntity);
//                                                    message(p,insertWechatLtjl(wechatLtjlEntity,conn)+"wx");
//                                                    message(p,+"wx");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            flg=0;
//                            获得下一个div selftable
                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null||elementTable.toString()=="<br>") {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
//                                message(p,"正在读取" +nextPagePath);
                                Document nextDocumentTable = null;
                                try {
                                    nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                } catch (IOException e) {
                                    flg = 1;
                                    System.out.println("跳出循环");
                                    break;
                                }

//                                System.out.println("正在读取" + nextPagePath);
                                elementTable = nextDocumentTable.body().child(0);
//                                message(p,elementTable.className());
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
//                                读到非短信息结束移到下一个outertable
                                for (int g = afterElementTable.length - 1; g > -1; g--) {
                                    if (elementTable.select("a").attr("name").startsWith(afterElementTable[afterElementTable.length - 1])) {
                                        flg = 0;
                                        break;
                                    }else{
                                        flg=1;//读到短信息结束跳出循环
                                    }
                                }
                                if (flg != 0) {
                                    break;
                                } else {
//                                    message(p,elementTable.text());
//                                    System.out.println(elementTable.select("table").text());
                                    qun=elementTable.text().substring(0,elementTable.text().lastIndexOf("("));
                                    elementTable = elementTable.nextElementSibling();
                                }
                            }
                            if (flg != 0) {
                                break;
                            }
                        }
                    }
                }
                System.out.println("qq、微信聊天记录读取完成!");
            }

        }

    }

    public static void wechatFriendsParser(String ai, TAutoJzxxEntity jzxx, TAutoWechatFriendsxxEntity wechatFriendsxxEntity, TAutoWechatZhxxEntity wechatZhxxEntity, String filePath, PrintStream p,Connection conn) throws IOException {

//                过滤掉qq
        if (ai.contains("849_50900~50999.ico")) {
//                查找基本信息字符,返回跳转文件及位置（扩展时候在这加一个找的内容的数组，循环查找遍历）
//                    先读普通好友列表 再读群成员列表
            String herf[] = jumpPage("好友列表", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件
                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取微信好友" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  微信好友信息不加
                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                message(p, beforeElementTables.toString());

//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
                String afterElementTables[] = herf[1].split("\\.");
                int afteri[] = new int[afterElementTables.length];
                String afterElementTable[] = new String[afterElementTables.length];
                for (int e = 0; e < afterElementTables.length; e++) {
                    afteri[e] = Integer.parseInt(afterElementTables[e]) + 1;
                    String temp = new String();
                    for (int f = 0; f < e; f++) {
                        temp += afterElementTables[f] + ".";
                    }
                    temp += String.valueOf(afteri[e]);
                    afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
                }
//                定位后找其后面要输入数据库的数据的表格
                if (beforeElementTables != null) {
                    for (Element w : beforeElementTables) {
//                            表格元素
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下

                        Element elementTable = w.parent().parent().parent().parent().parent().nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");
                        int flg = 0;
                        while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                            if (elementTable.select("table").attr("class").contains("OuterTable")) {
                                Elements trHeads = elementTable.getElementsByClass("tableheader");
                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element trHead : trHeads) {
//                                                过滤掉qq好友列表以及聊天记录里面的好友分类
                                    if (trHead.text().contains("微信ID")) {
                                        //微信好友列表
//                                        因为聊天记录里面也包含好友列表字符 通过表头过滤一下
                                        for (Element tr : trs) {
                                            wechatFriendsxxEntity.setNull();
//                                            message(p, "wx~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 6;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        break;
                                                    }
                                                    case 3: {
                                                        Elements tdOfinfos = td.select("tr");
                                                        for(Element tdOfinfo : tdOfinfos){
                                                            Element tdtype = tdOfinfo.child(0);
                                                            Element tdvalue=  tdOfinfo.child(1);
                                                            if(tdvalue.text() != "&nbsp" && tdvalue.text() != " " && tdvalue.text()!=null){
                                                                if(tdtype.text().contains("帐号")&&!tdtype.text().contains("QQ")){
                                                                    wechatFriendsxxEntity.setFdwechatno(tdvalue.text());
                                                                }
                                                                if(tdtype.text().contains("昵称")&&!tdtype.text().contains("QQ")){
                                                                    wechatFriendsxxEntity.setFriendnc(tdvalue.text());
                                                                }
                                                                if(tdtype.text().contains("备注")){
                                                                    wechatFriendsxxEntity.setFriendbz(tdvalue.text());
                                                                }
                                                                if(tdtype.text().contains("性别")){
                                                                    wechatFriendsxxEntity.setFriendsex(tdvalue.text());
                                                                }
                                                                if(tdtype.text().contains("个性签名")){
                                                                    wechatFriendsxxEntity.setFriendqm(tdvalue.text());
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 4) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                message(p, td.toString());
                                            }
                                            wechatFriendsxxEntity.setName(jzxx.getName());
                                            wechatFriendsxxEntity.setSfzhm(jzxx.getZjhm());
                                            wechatFriendsxxEntity.setSjhm(jzxx.getSjhm());
                                            wechatFriendsxxEntity.setSex(wechatZhxxEntity.getSex());
                                            wechatFriendsxxEntity.setWechatno(wechatZhxxEntity.getWechatno());
                                            if(wechatFriendsxxEntity.getFdwechatno()!=null){
                                                wechatFriendsxxEntity.setAj_id(jzxx.getAj_id());
                                                insertWechatFriends(wechatFriendsxxEntity,conn);
                                            }
                                        }
                                    } else {
                                        flg = 1;
                                        break;
                                    }
                                }
                            }
                            flg=0;
//                            获得下一个div selftable
                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null) {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
                                Document nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                elementTable = nextDocumentTable.body().child(0);
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
//                                读到非短信息结束移到下一个outertable
                                for (int g = afterElementTable.length - 1; g > -1; g--) {
                                    if (elementTable.select("a").attr("name").startsWith(afterElementTable[g])) {
                                        flg = 1;
                                        System.out.println("跳出循环");
                                        break;
                                    } //读到短信息结束跳出循环
                                }
                                if (flg != 0) {
                                    break;
                                } else {
//                                    message(p,elementTable.text());
                                    elementTable = elementTable.nextElementSibling();
                                }
                            }
                            if (flg != 0) {
                                break;
                            }
                        }
                    }
                }
            }
//                    读取群成员
            herf = jumpPage("群成员列表", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件
                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取微信群好友" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  qq账号信息不加
                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                message(p, "群" + beforeElementTables.toString());
                String qunhao = "";

//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
                String afterElementTables[] = herf[1].split("\\.");
                int afteri[] = new int[afterElementTables.length];
                String afterElementTable[] = new String[afterElementTables.length];
                for (int e = 0; e < afterElementTables.length; e++) {
                    afteri[e] = Integer.parseInt(afterElementTables[e]) + 1;
                    String temp = new String();
                    for (int f = 0; f < e; f++) {
                        temp += afterElementTables[f] + ".";
                    }
                    temp += String.valueOf(afteri[e]);
                    afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
                }
//                定位后找其后面要输入数据库的数据的表格
                if (beforeElementTables != null) {
                    for (Element w : beforeElementTables) {
//                            表格元素
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下
                        w = w.parent().parent().parent().parent().parent().nextElementSibling();
                        qunhao = w.text();
                        Element elementTable = w.nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");

                        int flg = 0;
                        while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                            if (elementTable.select("table").attr("class").contains("OuterTable")) {
                                Elements trHeads = elementTable.getElementsByClass("tableheader");
                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element trHead : trHeads) {
                                    if (trHead.text().contains("群帐号")) {
//                                        System.out.println("表头含qq继续");
                                        //qq好友列表

//                                        因为聊天记录里面也包含好友列表字符 通过表头过滤一下
                                        for (Element tr : trs) {
                                            wechatFriendsxxEntity.setNull();
//                                            message(p, "qq~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 5;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        wechatFriendsxxEntity.setFriendqh(td.text());
//                                                        wechatFriendsxxEntity.setFdwechatno(td.text());
                                                        break;
                                                    }
                                                    case 3: {
                                                        Elements tdOfinfos = td.select("tr");
                                                        for(Element tdOfinfo : tdOfinfos) {
                                                            Element tdtype = tdOfinfo.child(0);
                                                            Element tdvalue = tdOfinfo.child(1);
                                                            if (tdvalue.text() != "&nbsp" && tdvalue.text().trim() != "" && tdvalue.text() != null) {
                                                                if (tdtype.text().contains("成员帐号") && !tdtype.text().contains("QQ")) {
                                                                    wechatFriendsxxEntity.setFdwechatno(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("成员昵称") && !tdtype.text().contains("QQ")) {
                                                                    wechatFriendsxxEntity.setFriendnc(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("在本群的昵称")) {
                                                                    wechatFriendsxxEntity.setFriendbz(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("个性签名")) {
                                                                    wechatFriendsxxEntity.setFriendqm(tdvalue.text());
                                                                }
                                                                if(tdtype.text().contains("所在地")){
                                                                    wechatFriendsxxEntity.setFriendszd(tdvalue.text());
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }

                                                    default:
                                                        break;
//
                                                }
                                                if (i > 4) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                message(p, td.toString());
                                            }
                                            wechatFriendsxxEntity.setName(jzxx.getName());
                                            wechatFriendsxxEntity.setSfzhm(jzxx.getZjhm());
                                            wechatFriendsxxEntity.setSjhm(jzxx.getSjhm());
                                            wechatFriendsxxEntity.setSex(wechatZhxxEntity.getSex());
                                            wechatFriendsxxEntity.setWechatno(wechatZhxxEntity.getWechatno());
                                            if(wechatFriendsxxEntity.getFdwechatno()!=null){
                                                wechatFriendsxxEntity.setAj_id(jzxx.getAj_id());
                                                insertWechatFriends(wechatFriendsxxEntity,conn);
                                            }
                                        }
                                    } else {
                                        flg = 1;
                                        break;
                                    }
                                }
                            }

//                            获得下一个div selftable
                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null ) {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
//                                message(p,"正在读取" +nextPagePath);
                                Document nextDocumentTable = null;
                                try {
                                    nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                } catch (IOException e) {
                                    flg = 1;
                                    System.out.println("跳出循环");
                                    e.printStackTrace();
                                    break;
                                }
                                elementTable = nextDocumentTable.body().child(0);
//                                message(p,elementTable.className());
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
                                if(elementTable.text().contains("朋友圈")){
                                    flg=1;
                                }
//                                读到非短信息结束移到下一个outertable
                                for (int g = afterElementTable.length - 1; g > -1; g--) {
                                    if (elementTable.select("a").attr("name").startsWith(afterElementTable[g])) {
                                        flg = 1;//读到短信息结束跳出循环
                                        System.out.println("跳出循环");
                                        break;
                                    }
                                }
                                if (flg != 0) {
                                    break;
                                } else {
                                    qunhao = elementTable.text();
//                                    message(p,qunhao);
                                    elementTable = elementTable.nextElementSibling();
                                }
                            }
                            if (flg != 0) {
                                break;
                            }
                        }
                    }
                }
                System.out.println("微信好友读取完成!");
            }

        }

    }

    public static void qqFriendsParser(String ai, TAutoJzxxEntity jzxx, TAutoQqZhxxEntity qqZhxxEntity, TAutoQqFriendsxxEntity qqFriendsxxEntity, String filePath, PrintStream p,Connection conn) throws IOException {
//                过滤掉微信
        if (ai.contains("27_301~349.ico")) {
//                查找基本信息字符,返回跳转文件及位置（扩展时候在这加一个找的内容的数组，循环查找遍历）
//                    先读普通好友列表 再读群成员列表
            String herf[] = jumpPage("好友列表", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件
                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取qq好友信息" + herf[0]);
//                message(p,"正在读取qq好友信息" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  qq账号信息不加


                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                message(p, beforeElementTables.toString());

//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
                String afterElementTables[] = herf[1].split("\\.");
                int afteri[] = new int[afterElementTables.length];
                String afterElementTable[] = new String[afterElementTables.length];
                for (int e = 0; e < afterElementTables.length; e++) {
                    afteri[e] = Integer.parseInt(afterElementTables[e]) + 2;
                    String temp = new String();
                    for (int f = 0; f < e; f++) {
                        temp += afterElementTables[f] + ".";
                    }
                    temp += String.valueOf(afteri[e]);
                    afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
                }
//                定位后找其后面要输入数据库的数据的表格
                if (beforeElementTables != null) {
                    for (Element w : beforeElementTables) {
//                            表格元素
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下
                        w = w.parent().parent().parent().parent().parent().nextElementSibling();
                        Element elementTable = w.nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");
                        int flg = 0;
                        while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                            if (elementTable.select("table").attr("class").contains("OuterTable")) {
                                Elements trHeads = elementTable.select("tr:eq(0)");
                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element trHead : trHeads) {
//                                        System.out.println("表头含qq继续");
                                    //qq好友列表
                                    if (trHead.text().contains("好友号码")) {

//                                        因为聊天记录里面也包含好友列表字符 通过表头过滤一下
                                        for (Element tr : trs) {
                                            qqFriendsxxEntity.setNull();
//                                            message(p, "qq~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 6;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        qqFriendsxxEntity.setFdqq(td.text());
                                                        break;
                                                    }
                                                    case 3: {
                                                        Elements tdOfinfos = td.select("tr");
                                                        for (Element tdOfinfo : tdOfinfos) {
                                                            Element tdtype = tdOfinfo.child(0);
                                                            Element tdvalue = tdOfinfo.child(1);
                                                            if (tdvalue.text() != "" && tdvalue.text() != null && tdvalue.text().trim().length() > 0) {
                                                                if (tdtype.text().contains("好友昵称")) {
                                                                    qqFriendsxxEntity.setFriendqqnc(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("备注名")) {
                                                                    qqFriendsxxEntity.setQqfriendbz(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("个性签名") && !tdtype.text().contains("时间")) {
                                                                    qqFriendsxxEntity.setQqfriendgxqm(tdvalue.text());
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 5) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                message(p, td.toString());
                                            }
                                            qqFriendsxxEntity.setName(jzxx.getName());
                                            qqFriendsxxEntity.setSfzhm(jzxx.getZjhm());
                                            qqFriendsxxEntity.setSjhm(jzxx.getSjhm());
                                            qqFriendsxxEntity.setSex(qqZhxxEntity.getSex());
                                            qqFriendsxxEntity.setQq(qqZhxxEntity.getQq());
                                            if (qqFriendsxxEntity.getFdqq() != null) {
                                                qqFriendsxxEntity.setAj_id(jzxx.getAj_id());
                                                insertQQFriends(qqFriendsxxEntity, conn);
                                            }
                                        }
                                    }else{
                                        flg = 1;
                                        break;
                                    }
                                }

                            }
                            flg=0;

//                            获得下一个div selftable
                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null) {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
//                                message(p,"正在读取" +nextPagePath);
                                Document nextDocumentTable = null;
                                try {
                                    nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                } catch (IOException e) {
                                    flg = 1;
                                    System.out.println("跳出循环");
                                    e.printStackTrace();
                                    break;
                                }
                                elementTable = nextDocumentTable.body().child(0);
//                                message(p,elementTable.className());
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
                                if(elementTable.text().contains("群信息列表")){
                                    flg =1;
                                }
//                                读到非短信息结束移到下一个outertable
                                for (int g = afterElementTable.length - 1; g > -1; g--) {
                                    if (elementTable.select("a").attr("name").startsWith(afterElementTable[g])) {
                                        flg = 1;
                                        System.out.println("跳出循环");
                                        break;
                                    } //读到短信息结束跳出循环
                                }
                                if (flg != 0) {
                                    break;
                                } else {
//                                    message(p,elementTable.text());
                                    elementTable = elementTable.nextElementSibling();
                                }
                            }
                            if (flg != 0) {
                                break;
                            }
                        }
                    }
                }
            }
//                    读取群成员
            herf = jumpPage("群成员列表", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件
                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取qq群好友" + herf[0]);
//                message(p,"正在读取qq群好友" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  qq账号信息不加
                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                message(p, "群" + beforeElementTables.toString());
                String qunhao = "";
//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
                String afterElementTables[] = herf[1].split("\\.");
                int afteri[] = new int[afterElementTables.length];
                String afterElementTable[] = new String[afterElementTables.length];
                for (int e = 0; e < afterElementTables.length; e++) {
                    afteri[e] = Integer.parseInt(afterElementTables[e]) + 1;
                    String temp = new String();
                    for (int f = 0; f < e; f++) {
                        temp += afterElementTables[f] + ".";
                    }
                    temp += String.valueOf(afteri[e]);
                    afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
                }
//                定位后找其后面要输入数据库的数据的表格
                if (beforeElementTables != null) {
                    for (Element w : beforeElementTables) {
//                            表格元素
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下
                        w = w.parent().parent().parent().parent().parent().nextElementSibling();
                        qunhao = w.text();
                        Element elementTable = w.nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");
                        int flg = 0;
                        while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                            if (elementTable.select("table").attr("class").contains("OuterTable")) {
                                Elements trHeads = elementTable.select("tr:eq(0)");
                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element trHead : trHeads) {
                                    if (trHead.text().contains("群号码")) {
//                                        System.out.println("表头含qq继续");
                                        //qq好友列表
                                        qqFriendsxxEntity.setNull();
//                                        因为聊天记录里面也包含好友列表字符 通过表头过滤一下
                                        for (Element tr : trs) {
//                                            message(p, "qq~~~~~~~~~~~~~~~~" + tr.text());
                                            Elements tds = tr.children();
                                            int i = 0;//用于限制不嵌套读取列
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 8;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        qqFriendsxxEntity.setFdqq(td.text());
                                                        break;
                                                    }
                                                    case 3: {
                                                        qqFriendsxxEntity.setFriendqqnc(td.text());
                                                        break;
                                                    }
                                                    case 4: {
                                                        qqFriendsxxEntity.setQqfriendbz(td.text());
                                                        break;
                                                    }
                                                    case 5: {
                                                        qqFriendsxxEntity.setQqfriendqh(td.text());
                                                        break;
                                                    }
                                                    case 6:{
                                                        qqFriendsxxEntity.setQqfriendqshf(td.text());
                                                        break;
                                                    }
                                                    case 7:{
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 7) {
//                                            message(p, i + "");
                                                    break;
                                                }
//                                                message(p, td.toString());
                                            }
                                            qqFriendsxxEntity.setName(jzxx.getName());
                                            qqFriendsxxEntity.setSfzhm(jzxx.getZjhm());
                                            qqFriendsxxEntity.setSjhm(jzxx.getSjhm());
                                            qqFriendsxxEntity.setSex(qqZhxxEntity.getSex());
                                            qqFriendsxxEntity.setQq(qqZhxxEntity.getQq());
                                            qqFriendsxxEntity.setAj_id(jzxx.getAj_id());
                                            insertQQFriends(qqFriendsxxEntity,conn);
                                        }
                                    } else {
                                        flg = 1;
                                        break;
                                    }
                                }
                            }

//                            获得下一个div selftable
                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null) {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
//                                message(p,"正在读取" +nextPagePath);
                                Document nextDocumentTable = null;
                                try {
                                    nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                } catch (IOException e) {
                                    flg = 1;
                                    System.out.println("跳出循环");
                                    e.printStackTrace();
                                    break;
                                }
                                elementTable = nextDocumentTable.body().child(0);
//                                message(p,elementTable.className());
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
//                                读到非短信息结束移到下一个outertable
                                if(elementTable.text().contains("聊天记录")){
                                    flg =1;
                                }
                                for (int g = afterElementTable.length - 1; g > -1; g--) {
                                    if (elementTable.select("a").attr("name").startsWith(afterElementTable[g])) {
                                        flg = 1;//读到短信息结束跳出循环
                                        System.out.println("跳出循环");
                                        break;
                                    }
                                }
                                if (flg != 0) {
                                    break;
                                } else {
                                    qunhao = elementTable.text();
//                                    message(p,qunhao);
                                    elementTable = elementTable.nextElementSibling();
                                }
                            }
                            if (flg != 0) {
                                break;
                            }
                        }
                    }
                    System.out.println("qq好友信息录入完成");
                }
            }

        }

    }

    public static void qqWechatParser(String ai, TAutoJzxxEntity jzxx, TAutoQqZhxxEntity qqZhxxEntity, TAutoWechatZhxxEntity wechatZhxxEntity, String filePath, PrintStream p,Connection conn) throws IOException {
        String herf[] = jumpPage("帐号信息", ai).split("#");
        if (herf.length == 2) {
//                读取查找表格的文件
            String nextPagePath = herf[0];
            File inputTable = new File(filePath + herf[0]);
            Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
            System.out.println("正在读取qq、微信账号信息" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.  qq账号信息不加
            Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//            message(p,beforeElementTables.toString());

//                    要循环读入表格 限制一下结束!!!!!!!!!!   最后结束判断变复杂了。。。  不光最后那个+1
            String afterElementTables[] = herf[1].split("\\.");
            int afteri[] = new int[afterElementTables.length];
            String afterElementTable[] = new String[afterElementTables.length];
            for (int e = 0; e < afterElementTables.length; e++) {
                afteri[e] = Integer.parseInt(afterElementTables[e]) ;
                String temp = new String();
                for (int f = 0; f < e; f++) {
                    temp += afterElementTables[f] + ".";
                }
                temp += String.valueOf(afteri[e]);
                afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
            }
//                定位后找其后面要输入数据库的数据的表格
            if (beforeElementTables != null) {
                for (Element w : beforeElementTables) {
//                            表格元素
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下
                    Element elementTable = w.parent().parent().parent().parent().parent().nextElementSibling();
//                            message(p, w.parent().toString() + "~~~\n" + w.toString() + "~~~\n" + elementTable.text() + "~~~\n" + "!!!!!!!!!!!!!!!!\n");
                    while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                        if (elementTable.select("table").attr("class").contains("OuterTable")) {
                            Elements trHeads = elementTable.select("tr:eq(0)");
                            Elements trs = elementTable.select("tr:gt(0)");
                            for (Element trHead : trHeads) {
                                Element qqWechat = trHead.child(1);

                                int h = 0;  //用于限制不嵌套读取行
                                //qq账号信息
                                if (qqWechat.text().contains("QQ号码")) {
                                    qqZhxxEntity.setNull();
                                    for (Element tr : trs) {
//                                        message(p, "qq~~~~~~~~~~~~~~~~" + tr.text());
                                        Elements tds = tr.children();
                                        int i = 0;//用于限制不嵌套读取列
                                        if (h == 0) {
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 6;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        qqZhxxEntity.setQq(td.text());
                                                        break;
                                                    }
                                                    case 3: {
                                                        Elements tdOfinfos = td.select("tr");
                                                        for (Element tdOfinfo : tdOfinfos) {
//                                                            message(p,tdOfinfo.text());
                                                            Element tdtype = tdOfinfo.child(0);
                                                            Element tdvalue = tdOfinfo.child(1);
                                                            if (tdvalue.text() != "&nbsp" && tdvalue.text() != null) {
                                                                if(tdtype.text().contains("昵称")){
                                                                    qqZhxxEntity.setNicheng(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("关联帐号")) {
                                                                    qqZhxxEntity.setGlzh(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("年龄")) {
                                                                    qqZhxxEntity.setAge(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("所在地")) {
                                                                    qqZhxxEntity.setSzd(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("性别")) {
                                                                    qqZhxxEntity.setSex(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("密码")) {
                                                                    qqZhxxEntity.setMima(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("手机号码")) {
                                                                    qqZhxxEntity.setSjhm(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("生日")) {
                                                                    qqZhxxEntity.setBirthday(tdvalue.text());
                                                                }
                                                                if (tdtype.text().equals("个性签名")) {
                                                                    qqZhxxEntity.setGxqm(tdvalue.text());
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 4) {
//                                            message(p, i + "");
                                                    break;
                                                }
                                            }
                                        }
                                        h++;
                                    }
                                    qqZhxxEntity.setName(jzxx.getName());
                                    qqZhxxEntity.setSfzhm(jzxx.getZjhm());
                                    if (qqZhxxEntity.getSjhm() == null) {
                                        qqZhxxEntity.setSjhm(jzxx.getSjhm());
                                    }
                                    qqZhxxEntity.setAj_id(jzxx.getAj_id());
                                    insertQQ(qqZhxxEntity,conn);
                                    h = 0;
                                    break;
                                }//微信账号信息
                                else if(qqWechat.text().contains("微信号")){
                                    wechatZhxxEntity.setNull();
                                    for (Element tr : trs) {
//                                        message(p, "微信~~~~~~~~~~~~~~~~" + tr.text());
                                        Elements tds = tr.children();
                                        int i = 0;//用于限制不嵌套读取列
                                        if (h == 0) {
                                            for (Element td : tds) {
//                                                        message(p,td.text());
                                                i++;
                                                switch (i) {
                                                    case 1: {
                                                        //第一列要是不是数字就跳出  内嵌了列
                                                        if (!td.text().matches("[0-9]{0,9}")) {
                                                            i = 6;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        wechatZhxxEntity.setWxh(td.text());
//                                                        System.out.println(wechatZhxxEntity.getWechatno());
                                                        break;
                                                    }
                                                    case 3: {
                                                        Elements tdOfinfos = td.select("tr");
                                                        for (Element tdOfinfo : tdOfinfos) {
//                                                            message(p,tdOfinfo.text());
                                                            Element tdtype = tdOfinfo.child(0);
                                                            Element tdvalue = tdOfinfo.child(1);
                                                            if (tdvalue.text() != "&nbsp" && tdvalue.text() != null) {
                                                                if (tdtype.text().contains("密钥")) {//因为被包含于算法类型  所以放在后面而且break
                                                                    if (tdtype.text().contains("密钥算法类型")) {
                                                                        wechatZhxxEntity.setMysflx(tdvalue.text());
                                                                    } else {
                                                                        wechatZhxxEntity.setMiyao(tdvalue.text());
                                                                    }
                                                                }
                                                                if(tdtype.text().contains("昵称")){
                                                                    wechatZhxxEntity.setNicheng(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("微信ID")) {
                                                                    wechatZhxxEntity.setWechatno(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("QQ号码")) {
                                                                    wechatZhxxEntity.setQq(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("邮箱地址")) {
                                                                    wechatZhxxEntity.setEmail(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("手机号")) {
                                                                    wechatZhxxEntity.setSjhm(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("省")) {
                                                                    wechatZhxxEntity.setSheng(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("个性签名")&&(!tdtype.text().contains("时间"))) {
                                                                    wechatZhxxEntity.setGxqm(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("性别")) {
                                                                    wechatZhxxEntity.setSex(tdvalue.text());
                                                                }
                                                                if (tdtype.text().contains("市")) {
                                                                    wechatZhxxEntity.setShi(tdvalue.text());
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default:
                                                        break;
//
                                                }
                                                if (i > 4) {
//                                            message(p, i + "");
                                                    break;
                                                }
                                            }
                                        }
                                        h++;
                                    }
                                    wechatZhxxEntity.setName(jzxx.getName());
                                    wechatZhxxEntity.setSfzhm(jzxx.getSjhm());
                                    if (wechatZhxxEntity.getSjhm() == null) {
                                        wechatZhxxEntity.setSjhm(jzxx.getSjhm());
                                    }
                                    wechatZhxxEntity.setAj_id(jzxx.getAj_id());
                                    insertWechat(wechatZhxxEntity,conn);
                                    h = 0;
                                    break;
                                }
                            }
//                                    message(p, thdq.getpName() + "\t" + thdq.getpNumber() + "\t" + thdq.getCallDate() + "\t" + thdq.getCallTime() + "\t" + thdq.getCallType() + "\t" + thdq.getCallValue());

                        }

//                            获得下一个div selftable
                        elementTable = elementTable.nextElementSibling();
                        if (elementTable == null) {
                            //读到本文件结束 打开下一个html继续读
                            String temp[] = nextPagePath.split("\\.");
                            int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                            nextPagePath = "Contents" + nextPageNum + ".html";
                            File nextPage = new File(filePath + nextPagePath);
                            System.out.println("正在读取" + nextPagePath);
//                            message(p,"正在读取" +nextPagePath);
                            Document nextDocumentTable = null;
                            try {
                                nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                            } catch (IOException e) {
//                                flg = 1;
                                System.out.println("跳出循环");
                                e.printStackTrace();
                                break;
                            }
                            elementTable = nextDocumentTable.body().child(0);
                            break;
//                            message(p,elementTable.className());
                        }
                        if (elementTable.select("table").attr("class").contains("selfTable")) {
                            int flg = 1;
//                                读到非短信息结束移到下一个outertable
                            break;
                        }
                    }
                }
                System.out.println("qq、微信账号信息录入完成");
            } else {
                System.out.println("未找到" + herf[1]);
            }
//            System.out.println(qqZhxxEntity.getQq()+"qq账号信息、"+wechatZhxxEntity.getWechatno()+"微信账号信息录入完成");
        }
    }

    public static void thqdParser(String ai, TAutoJzxxEntity jzxx, TAutoThqdEntity thdq, String filePath, PrintStream p, Connection conn) {
        String herf[]= jumpPage("通话记录",ai).split("#");
        if (herf.length==2){
//                读取查找表格的文件
            String nextPagePath=herf[0];
            File inputTable = new File(filePath+herf[0]);
            Document documentTable = null;
            try {
                documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取通话清单"+herf[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.
            Elements beforeElementTables=documentTable.select("a[name="+herf[1]+"]");
//                    要循环读入表格 限制一下结束!!!!!!!!!!
            String afterElementTables[] = herf[1].split("\\.");
            int afteri[] = new int[afterElementTables.length];
            String afterElementTable[] = new String[afterElementTables.length];
            for (int e = 0; e < afterElementTables.length; e++) {
                afteri[e] = Integer.parseInt(afterElementTables[e]) + 1;
                String temp = new String();
                for (int f = 0; f < e; f++) {
                    temp += afterElementTables[f] + ".";
                }
                temp += String.valueOf(afteri[e]);
                afterElementTable[e] = temp;//结束表格读入位置  可能很多
//                        System.out.println(afterElementTable[e]);
            }
//            System.out.println(afterElementTable);
//                定位后找其后面要输入数据库的数据的表格
            for (Element w:beforeElementTables) {
                w = w.parent().parent().parent().parent().parent().nextElementSibling();
//                            表格元素
                Pattern pattern1 = Pattern.compile("^全部通话记录.*$");
                Matcher matcher1 = pattern1.matcher(w.text());
                if(matcher1.find()) {
//                        通话这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下]
                    Element elementTable = w.nextElementSibling();
//                        message(p, w.parent().toString() + "~~~\n" + w.toString()+"~~~\n"+elementTable.text()+"~~~\n"+"!!!!!!!!!!!!!!!!\n");
                    while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                        if (elementTable.select("table").attr("class").contains("OuterTable")) {
                            Elements trs = elementTable.select("tr:gt(0)");
                            for (Element tr : trs) {
                                thdq.setNull();
//                            message(p,"~~~~~~~~~~~~~~~~"+tr.text());
                                Elements tds = tr.children();
                                int i = 0;
                                String datatype = new String();
                                for (Element td : tds) {
//                                message(p,td.text());
                                    i++;
                                    switch (i) {
                                        case 1: {
                                            //第一列要是不是数字就跳出  内嵌了列
                                            if (!td.text().matches("[0-9]{0,9}")) {
                                                i = 5;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2: {
                                            thdq.setpName(td.text());
                                            break;
                                        }
                                        case 3: {
//                                        根据图片获得通话类型
                                            String sendType[] = td.select("img").attr("src").split("/|\\.");
                                            if (sendType.length > 2) {
                                                if (sendType[1].contains("DialogAnswer")) {
                                                    thdq.setCallType("被叫");
                                                } else if (sendType[1].contains("DialogCall")) {
                                                    thdq.setCallType("主叫");
                                                } else if (sendType[1].contains("DialogMiss")) {
                                                    thdq.setCallType("被叫");
                                                }
                                            }

                                            String hm = td.text().replace("-", "").replace("+86", "").replace(" ", "").replace("+", "");
                                            thdq.setpNumber(hm);
                                            break;
                                        }
                                        case 4: {
//                                        Elements tdOfinfos=td.select("tr");
//                                        message(p,"tr4%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//                                        for (Element tdOfinfo:tdOfinfos
//                                                ) {
////                                            message(p,tdOfinfo.text());
//                                            Element tdtype = tdOfinfo.child(0);
//                                            Element tdvalue = tdOfinfo.child(1);
                                            if (td.text() != "&nbsp") {
                                                String thTime[] = td.text().split(" ");
                                                if (thTime.length == 2) {
                                                    thdq.setCallDate(thTime[0]);
                                                    thdq.setCallTime(thTime[1]);
                                                }
                                            }
//                                                if (tdtype.text().contains("持续时间")){
//                                                    thdq.setCallValue(tdvalue.text());
//                                                }
//                                            }
//                                        }
//                                        break;
                                        }
                                        case 5: {
                                            if (td.text() != "") {
                                                thdq.setCallValue(td.text());
                                            }
                                        }
                                        default:
                                            break;
//
                                    }
                                    if (i > 5) {
//                                            message(p, i + "");
                                        break;
                                    }
                                    thdq.setuName(jzxx.getName());
                                    thdq.setuNumber(jzxx.getSjhm());
//                              thdq.setDataType(datatype);
//                                thdq.setInsertTime(new Timestamp(System.currentTimeMillis()).toString());
                                }
                                if (thdq.getCallValue() != null) {
//                                message(p,thdq.getpName()+"\t"+thdq.getpNumber()+"\t"+thdq.getCallDate()+"\t"+thdq.getCallTime()+"\t"+thdq.getCallType()+"\t"+thdq.getCallValue());
                                    thdq.setAj_id(jzxx.getAj_id());
                                    insertThqd(thdq, conn);
                                }
                            }
                        }
//                            获得下一个div selftable
                        elementTable = elementTable.nextElementSibling();
                        if (elementTable == null || elementTable.toString() == "<br>") {
                            //读到本文件结束 打开下一个html继续读
                            String temp[] = nextPagePath.split("\\.");
                            int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                            nextPagePath = "Contents" + nextPageNum + ".html";
                            File nextPage = new File(filePath + nextPagePath);
                            Document nextDocumentTable = null;
                            try {
                                nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                System.out.println("正在读取" + nextPagePath);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            elementTable = nextDocumentTable.body().child(0);
                        }
                        if (elementTable.select("table").attr("class").contains("selfTable")) {
//                        System.out.println(elementTable.toString());
                            int flg = 1;
//                                读到非短信息结束移到下一个outertable
                            for (int g = afterElementTable.length - 1; g > -1; g--) {
//                            System.out.println(afterElementTable[g]);
                                if (elementTable.select("a").attr("name").startsWith(afterElementTable[g])) {
                                    flg = 1;
                                    System.out.println("跳出循环" + afterElementTable[g]);
                                    break;
                                } //读到短信息结束跳出循环
                            }

                            if (flg != 0) {
                                System.out.println("跳出循环");
                                break;
                            } else {
                                elementTable = elementTable.nextElementSibling();
                            }
                        }
                    }
                }
            }
            System.out.println("手机通话记录录入完成");
        }
    }

    public static void dxParser(String ai, TAutoJzxxEntity jzxx, TAutoDxEntity dx, String filePath, PrintStream p, Connection conn) throws IOException {
        String herf[]= jumpPage("短信息",ai).split("#");
        if (herf.length==2){
//                读取查找表格的文件
            String nextPagePath=herf[0];
            File inputTable = new File(filePath+herf[0]);
            Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
            System.out.println("正在读取"+herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.
            Elements beforeElementTables=documentTable.select("a[name="+herf[1]+"]");
//                    要循环读入表格 限制一下结束!!!!!!!!!!
            String afterElementTables[]=herf[1].split("\\.");
            int afteri=Integer.parseInt(afterElementTables[afterElementTables.length-1])+1;
            String afterElementTable=new String();
            for (int c=0;c<afterElementTables.length-1;c++){
                afterElementTable+=afterElementTables[c]+".";
            }
            afterElementTable+=String.valueOf(afteri);//结束表格读入位置
//                定位后找其后面要输入数据库的数据的表格
            for (Element w:beforeElementTables) {
//                            表格元素
                w = w.parent().parent().parent().parent().parent().nextElementSibling();
                Pattern pattern1 = Pattern.compile("^全部短信.*$");
                Matcher matcher1 = pattern1.matcher(w.text());
                if(matcher1.find()) {
//                        短信这个表格元素很复杂!!!! td内部还有嵌套 根据输出文件分析了一下
                    Element elementTable = w.nextElementSibling();
//                        message(p, w.parent().toString() + "~~~\n" + w.toString()+"~~~\n"+elementTable.toString()+"~~~\n"+"!!!!!!!!!!!!!!!!\n");
                    while (elementTable != null) {
//                        在循环中判断是否是需要的表格 再进行处理
                        if (elementTable.select("table").attr("class").contains("OuterTable")) {
                            Elements trs = elementTable.select("tr:gt(0)");
                            for (Element tr : trs) {
                                dx.setNull();
//                            message(p,"~~~~~~~~~~~~~~~~"+tr.text());
                                Elements tds = tr.children();
                                int i = 0;
                                String datatype = new String();
                                for (Element td : tds) {
//                                message(p,td.text());
                                    i++;
                                    switch (i) {
                                        case 1: {
                                            //第一列要是不是数字就跳出  内嵌了列
                                            if (!td.text().matches("[0-9]{0,9}")) {
                                                i = 7;
//                                            message(p,String.valueOf(i));
                                                break;
                                            }
                                            break;
                                        }
                                        case 2: {
//                                        根据图片获得发送类型
                                            String sendType[] = td.select("img").attr("src").split("/|\\.");
                                            if (sendType.length > 2) {
                                                if (sendType[1].contains("SmsReceive")) {
                                                    dx.setSendType("被叫");
                                                } else if (sendType[1].contains("SmsSend")) {
                                                    dx.setSendType("主叫");
                                                }
                                            }
//                                        获得发送号码
                                            String infos[] = td.text().split(" ");
                                            for (int j = 0; j < infos.length; j++) {
                                                if (infos[j].matches("[0-9]+")) {
                                                    String hm = infos[j].replace("-", "").replace("+86", "").replace(" ", "").replace("+", "");
                                                    dx.setpNumber(hm);
                                                    if (j > 0) {
                                                        dx.setpName(infos[j - 1]);
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            dx.setSendTime(td.text());
                                            break;
                                        }
                                        case 4: {
                                            String con = td.text().replace("短信内容", "").replace("来源", "")
                                                    .replace("归属地", "");
                                            dx.setContent(con);
                                            break;
                                        }
                                        case 5: {
                                            datatype += td.text();
                                            break;
                                        }
                                        case 6: {
                                            if (td.text().indexOf("是") != -1) {
                                                datatype += "已删除";
                                                break;
                                            }
                                        }
                                        default:
                                            break;

                                    }
                                    if (i > 6) {
//                                    message(p,i+"");
                                        break;
                                    }
                                    dx.setuName(jzxx.getName());
                                    dx.setuNumber(jzxx.getSjhm());
//                                dx.setDataType(datatype);
                                    dx.setInsertTime(new Timestamp(System.currentTimeMillis()).toString());
                                }
                                if (dx.getpNumber() != null && dx.getpNumber().trim().length()>0) {
//                                message(p,dx.getContent()+"\t"+dx.getDataType()+"\t"+dx.getpName()+"\t"+dx.getpNumber()+"\t"+dx.getSendType()+"\t"+dx.getSendTime());
                                    dx.setAj_id(jzxx.getAj_id());
                                    insertDx(dx, conn);
                                }
                            }
                        }
//                            获得下一个div selftable
                        elementTable = elementTable.nextElementSibling();
                        if (elementTable == null) {
                            //读到本文件结束 打开下一个html继续读
                            String temp[] = nextPagePath.split("\\.");
                            int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                            nextPagePath = "Contents" + nextPageNum + ".html";
                            File nextPage = new File(filePath + nextPagePath);
                            System.out.println("正在读取" + nextPagePath);
//                        message(p,"正在读取" +nextPagePath);
                            Document nextDocumentTable = null;
                            try {
                                nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                            } catch (IOException e) {
//                            flg = 1;
                                System.out.println("跳出循环");
                                e.printStackTrace();
                                break;
                            }
                            elementTable = nextDocumentTable.body().child(0);
//                        message(p,elementTable.className());
                        }
                        if (elementTable.select("table").attr("class").contains("selfTable")) {
//                                读到非短信息结束移到下一个outertable
                            break;
//                            if (!elementTable.select("a").attr("name").startsWith(afterElementTable)) {
//                                elementTable = elementTable.nextElementSibling();
//                            } else {
//                                System.out.println("跳出循环");
//                                break;
//                            }//读到短信息结束跳出循环
                        }
                    }
                }
            }
            System.out.println("手机短信录入完成");
        }
    }

    public static void txlParser(String ai, TAutoJzxxEntity jzxx, TAutoTxlEntity txl, String filePath, PrintStream p, Connection conn) throws IOException {
        if(ai.contains("11_100_150_469.ico")||ai.contains("src/-9999.ico")) {
            String herf[] = jumpPage("通讯录", ai).split("#");

            if (herf.length == 2) {
//                读取查找表格的文件
                String nextPagePath = herf[0];
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取通讯录" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.

                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");


//                    要循环读入表格 限制一下结束!!!!!!!!!!
                String afterElementTables[] = herf[1].split("\\.");
                int afteri = Integer.parseInt(afterElementTables[afterElementTables.length - 1]) + 1;
                String afterElementTable = new String();
                for (int c = 0; c < afterElementTables.length - 1; c++) {
                    afterElementTable += afterElementTables[c] + ".";
                }
                afterElementTable += String.valueOf(afteri);//结束表格读入位置
//                定位后找其后面要输入数据库的数据的表格
                for (Element w : beforeElementTables) {

                    w = w.parent().parent().parent().parent().parent().nextElementSibling();


                    Pattern pattern1 = Pattern.compile("^手机.*$");
                    Matcher matcher1 = pattern1.matcher(w.text());
//                System.out.println(w.text());
                    if (matcher1.find()) {
//                    System.out.println(matcher1.group(0) + "!!!!!!!!!!!");
//                            表格元素
                        Element elementTable = w.nextElementSibling();
//                    message(p, w.parent().toString() + "~~~\n" + w.toString()+"~~~\n"+"~~~\n"+"!!!!!!!!!!!!!!!!\n");
                        while (elementTable != null) {
                            if (elementTable.select("table").attr("class").contains("OuterTable")) {

                                Elements trs = elementTable.select("tr:gt(0)");
                                for (Element tr : trs) {
                                    txl.setNull();
//                找表头之后的每一行第二第三列 第二列为姓名 第三列为电话
                                    Element tdname = tr.child(1);
                                    Element tdnumber = tr.child(2);
                                    Element tdDeltype = tr.child(5);
                                    if (tdname.text() != "&nbsp") {
                                        txl.setuName(jzxx.getName());
                                        txl.setuNumber(jzxx.getSjhm());
                                        txl.setpName(tdname.text());
                                        if (tdnumber.text() != "&nbsp") {
                                            String num[] = tdnumber.text().split("<br>| |;");
                                            String hm = num[0].replace("-", "").replace("+86", "").replace(" ", "").replace("+", "");
                                            txl.setpNumber1(hm);
                                            if (num.length == 2) {
                                                String hm1 = num[1].replace("-", "").replace("+86", "").replace(" ", "").replace("+", "");
                                                txl.setpNumber2(hm1);
                                            }
                                        }
                                        if (tdDeltype.text().indexOf("是") != -1) {
//                                        txl.setDataType("已删除");
                                        }
                                    }
//                                txl.setInsertTime(new Timestamp(System.currentTimeMillis()).toString());
//                                message(p, txl.getpName());
                                    txl.setAj_id(jzxx.getAj_id());
                                    insertTxl(txl, conn);
                                }
                            }

                            elementTable = elementTable.nextElementSibling();
                            if (elementTable == null) {
                                //读到本文件结束 打开下一个html继续读
                                String temp[] = nextPagePath.split("\\.");
                                int nextPageNum = Integer.parseInt(temp[0].substring(8)) + 1;
                                nextPagePath = "Contents" + nextPageNum + ".html";
                                File nextPage = new File(filePath + nextPagePath);
                                System.out.println("正在读取" + nextPagePath);
                                Document nextDocumentTable = null;
                                try {
                                    nextDocumentTable = Jsoup.parse(nextPage, "UTF-8", "http://example.com/");
                                } catch (IOException e) {
//                                flg = 1;
                                    System.out.println("跳出循环");
                                    e.printStackTrace();
                                    break;
                                }
                                elementTable = nextDocumentTable.body().child(0);
                            }
                            if (elementTable.select("table").attr("class").contains("selfTable")) {
//                                读到非短信息结束移到下一个outertable
                                if (!elementTable.select("a").attr("name").startsWith(afterElementTable)) {
                                    elementTable = elementTable.nextElementSibling();
                                    if(elementTable.select("a").text().contains("通话记录")){
                                        break;
                                    }
                                } else {
                                    System.out.println("跳出循环");
                                    break;
                                }//读到短信息结束跳出循环
                            }

                        }
                    }
                }
//            System.out.println(jzxx.getIccid()+"手机通讯录录入完成");
                System.out.println("手机通讯录录入完成");
            }
        }
    }

    public static void jzxxParser(String ai, TAutoJzxxEntity jzxx, RoleEntity roleEntity, String filePath, PrintStream p, Connection conn, String name) throws IOException {

//         查找基本信息字符,返回跳转文件及位置（扩展时候在这加一个找的内容的数组，循环查找遍历）
        if(ai.contains("基本信息")) {
            String herf[] = jumpPage("基本信息", ai).split("#");
            if (herf.length == 2) {
//                读取查找表格的文件
                File inputTable = new File(filePath + herf[0]);
                Document documentTable = Jsoup.parse(inputTable, "UTF-8", "http://example.com/");
                System.out.println("正在读取机主信息" + herf[0]);
//                按刚刚的返回值按name属性找a标签定位
//                table[0]基本信息要+1. ，即name="+herf[1]+"1.
                Elements beforeElementTables = documentTable.select("a[name=" + herf[1] + "]");
//                定位后找其后面要输入数据库的数据的表格
                for (Element w : beforeElementTables) {
//                        表格元素
                    Element elementTable = w.parent().parent().parent().parent().parent().nextElementSibling();
//                message(p, w.parent().toString() + "~~~\n" + w.toString()+"~~~\n"+elementTable.toString()+"~~~\n"+"!!!!!!!!!!!!!!!!\n");
                    jzxx.setNull();
                    Elements trs = elementTable.select("tr:gt(0)");
//                找表头之后的每一行第二第三列 第二列用于匹配 第三列匹配出即为值
                    for (Element tr : trs) {
                        Element tdtype = tr.child(1);
                        Element tdvalue = tr.child(2);
                        String tdvString = tdvalue.text();
                        if (!tdvString.equals("&nbsp;") && !tdvString.equals("")) {

                            if (tdtype.text().contains("设备名称")) {
//                            jzxx.setName(null);
                                ///改多部
//                            jzxx.setNull();
                                jzxx.setSbbh(tdvalue.text());

                                if (name.contains("多部手机")) {
                                    if (tdvalue.text().contains("iPhone")) {
                                        jzxx.setName("汤宇飞");
                                    } else {
                                        jzxx.setName(tdvalue.text());
                                    }
//                                jzxx.setNull();
                                }
//                                break;
                            }

                            if (tdtype.text().contains("检材持有人")) {
                                if (tdvalue.text().length() > 0) {
                                    jzxx.setName(tdvalue.text());
                                }
                            }

                            if (tdtype.text().contains("本机号码")) {
                                String hm = tdvalue.text().replace("+86", "");
                                String hm1 = hm.replace("-", "");
                                String hm2 = hm1.replace(" ", "");
//                            System.out.println(tdvalue.text()+"处理前"+hm2);
                                if (hm2.length() > 5) {
//                                System.out.println(tdvalue.text()+"处理后"+hm2);
                                    jzxx.setSjhm(hm2);
//                                break;
                                }

                            }
                            if (tdtype.text().contains("证件类型")) {
                                jzxx.setZjlx(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("证件编号")) {
                                jzxx.setZjhm(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("创建时间")) {
                                jzxx.setCjsj(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("备注")) {
                                jzxx.setBeizhu(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("手机型号")) {
                                jzxx.setSjxh(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("IMSI")) {
                                jzxx.setSbsbm(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("IMEI/MEID")) {
                                jzxx.setYhsbm(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("Wi-Fi MAC地址")) {
                                jzxx.setMac(tdvalue.text());
//                                break;
                            }
                            if (tdtype.text().contains("设备ID")) {
                                jzxx.setSbbh(tdvalue.text());
//                                break;
                            }

                        }
                    }
                    jzxx.setInsertTime(TimeFormatUtil.getDate("-"));
                    roleEntity.setSfzhm(jzxx.getZjhm());
                    roleEntity.setRole_name(jzxx.getName());
                    if(roleEntity.getSfzhm()==null){
                        roleEntity.setSfzhm("未获取到信息");
                    }
//                    rs.addRole(roleEntity);
                    jzxx.setAj_id(roleEntity.getRole_id());
                    insertJzxx(jzxx, conn);
//                message(p,jzxx.toString());
//                System.out.println(jzxx.getName());
//                System.out.println(jzxx.getYhsbm());
                }
                System.out.println("手机机主信息录入完成！");
            }
        }
    }
}
