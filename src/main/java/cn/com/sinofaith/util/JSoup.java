package cn.com.sinofaith.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class JSoup {

    public static void main(String[] args) throws Exception {
        //String filePath = "C:\\Users\\47435\\Desktop\\字段映射自动测试\\案件（20190508172909）_20190509191832_html\\Report\\";//report 路径 D:\\取证报告\\陈飞\\Report\\
        //File input = new File(filePath + "Catalog.html");
        //System.out.println("开始读入" + filePath + "Catalog.html");
        //Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
        //Elements scripts = document.getElementsByTag("script");
        //String s;
        //for (Element script : scripts) {
        //    //            System.out.println("!!!");
        //    s = script.data();
        //    String a[] = s.split("\\n");
        //    for (String ai : a) {
        //        if(ai.contains("通讯录"))
        //        System.out.println(ai);
        //    }
        //}
        Document document = Jsoup.parse(new File("C:\\Users\\47435\\Desktop\\test.html"), "UTF-8", "http://example.com/");
        Elements trs = document.select("table.OuterTableList > tbody > tr");
        for(Element tr : trs){
            if(tr.hasClass("tableheader")){
                continue;
            }
            String[] fsf = tr.child(1).text().split("\\(|\\)");
            String[] jsf = tr.child(2).text().split("\\(|\\)");
            System.out.println(fsf[0] +"  "+ fsf[1]);
            System.out.println(jsf[0] +"  "+ jsf[1]);
            System.out.println(tr.child(3).text());
            if(tr.children().size()>4){
                Element e = tr.child(4);
                Elements tds = e.select("table > tbody > tr > td:eq(1)");
                String content  = "";
                String lx ="";
                if(tds.size()>0){
                    if(tds.size()==4){
                        content = tds.get(0).text();
                        lx = tds.get(1).text();
                        System.out.println(content);
                        System.out.println(lx);
                    }
                    if(tds.size()==5){
                        if(tds.select("quote > a").size()==1){
                            content = tds.select("quote > a").get(0).attr("href");
                            lx = content.substring(content.lastIndexOf("."));
                            System.out.println(content);
                            System.out.println(lx);

                        }else{
                            content = tds.select("quote").text();
                            lx = "文本";
                            System.out.println(content);
                            System.out.println(lx);

                        }
                    }
                }
            }
        }
    }
}
