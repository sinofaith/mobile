package cn.com.sinofaith.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.channels.FileChannel;

public class Test {
    public static void main(String[] args) throws Exception{
        long a= System.currentTimeMillis();
        System.out.println(a);
        String startPath = "D:\\work\\html\\手机\\上海咖啡汪俊杰_HTML\\上海咖啡汪俊杰_20180926170835_html\\Report\\Data\\7818eb9139c20205406ec77bc82148db65e3e71b\\wechat\\wxid_4ehzpmqo1ca012\\Audio\\0c8e135b492a73120d40a16b0e4eb8a5\\";
        String endPath = "C:\\Users\\47435\\Desktop\\tt\\";
        File file = new File(startPath);
        for(int i=0;i<file.list().length;i++) {
            nioTransferCopy(new File(startPath+file.list()[i]),new File(endPath+file.list()[i]));
        }
        System.out.println(System.currentTimeMillis()-a);
        long b= System.currentTimeMillis();
        for(int i=0;i<file.list().length;i++){
            FileUtils.copyFile(new File(startPath+file.list()[i]),new FileOutputStream(endPath+file.list()[i]));
        }
        System.out.println(System.currentTimeMillis()-b);
        long c = System.currentTimeMillis();
        for (int i=0;i<file.list().length;i++){
            Amr2Mp3.changeToMp3(startPath+file.list()[i],endPath+file.list()[i].replace("amr","mp3"));
        }
        System.out.println(System.currentTimeMillis()-c);
    }


    private static void nioTransferCopy(File source, File target) throws Exception{
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inStream.close();
            in.close();
            outStream.close();
            out.close();
        }
    }
}
