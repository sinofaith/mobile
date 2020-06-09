package cn.com.sinofaith.util;

import it.sauronsoftware.jave.*;

import java.io.File;

public class Amr2Mp3 {
    public static void main(String[] args) throws Exception {
        String path1 = "D:\\work\\html\\假药\\导出案件_20180529181555\\案件（20180529172330）\\取证报告\\陈飞\\Report\\Data\\1607-A01\\qq\\928763001\\QQfile_recv\\AndroidQQ\\Tencent\\MobileQQ\\928763001\\ptt\\group_20180519214453857.amr";
        String path2 = "C:\\Users\\47435\\Desktop\\37.mp3";
        changeToMp3(path1, path2);
    }

    public static void changeToMp3(String sourcePath, String targetPath) {
        File source = new File(sourcePath);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();

        audio.setCodec("libmp3lame");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);

        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            System.out.println(source);
        }
    }
}
