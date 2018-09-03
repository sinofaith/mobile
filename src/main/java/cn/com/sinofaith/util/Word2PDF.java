package cn.com.sinofaith.util;

import com.aspose.words.License;

import java.io.*;
import java.util.ResourceBundle;

import com.aspose.words.*;
public class Word2PDF {
    private static final String FILEPATH;

    static {

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

        FILEPATH = bundle.getString("jdbc.path");
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Word2PDF.class.getResourceAsStream("/license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void doc2pdf(String Address,String fileName) {

//        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return;
//        }

        try {
            long old = System.currentTimeMillis();
            File file = new File(FILEPATH+fileName+".pdf");  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(Address);                    //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
