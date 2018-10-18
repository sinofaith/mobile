package cn.com.sinofaith.controller;

import cn.com.sinofaith.bean.*;
import cn.com.sinofaith.service.UploadServices;
import cn.com.sinofaith.service.brand.RegionService;
import cn.com.sinofaith.util.DBUtil;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;


@RequestMapping("/Upload")
@Controller
public class UploadController {
    @Autowired
    private RegionService rs;
    @Autowired
    private UploadServices us;

    @RequestMapping(value = "/importmeiya",method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndView main(@RequestParam("file") MultipartFile[] mywjlj, String myjzm,
                                    String mysjy,String role, HttpServletRequest req,HttpSession session) throws IOException, SQLException {
        //        输出测试文件
        ModelAndView mvc = new ModelAndView("redirect:/case/seach?pageNo=1");
        FileOutputStream fs = new FileOutputStream(new File("jSoupTextResult.txt"));
        PrintStream p = new PrintStream(fs);
        String file =  "D:\\16-17\\intern\\ieven\\html1\\";
        String name = "test";
        String datatype = "";
        String uploadPath = req.getSession().getServletContext().getRealPath("/")+"upload/temp/"+System.currentTimeMillis();
        String fname = "";
        String directory = "";
        File file1;
        try {
            for (MultipartFile f : mywjlj) {
//            File file2;
                if (f instanceof CommonsMultipartFile) {
                    CommonsMultipartFile f2 = (CommonsMultipartFile) f;
                    fname = f2.getFileItem().getName();
                    directory = fname.substring(0, fname.lastIndexOf("/"));
                    file1 = new File(uploadPath + "/" + directory);
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    file1 = new File(uploadPath + "/" + fname);
                    file1.createNewFile();
                    f.transferTo(file1);

                }
            }
            String filePath = uploadPath + "/" + fname.split("/")[0] + "/";
            name = myjzm;
            datatype = mysjy;


            //初始化jsoup 找到目录里面的js:dtree
            File input = new File(filePath + "Catalog.html");
            System.out.println("开始读入" + mywjlj + "Catalog.html");
            Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements scripts = document.getElementsByTag("script");
            String s;
            RoleEntity roleEntity = new RoleEntity();
            Integer regionId = (Integer) session.getAttribute("regionId");
            long id = Long.parseLong(regionId.toString());
            // 设置一个添加人员对象
            roleEntity.setRegion_id(id);
            roleEntity.setRole(role);
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
//
            jzxx.setName(myjzm);
            jzxx.setDataType(datatype);
            dx.setDataType(datatype);
            qqFriendsxxEntity.setDataType(datatype);
            qqLtjlEntity.setDataType(datatype);
            qqZhxxEntity.setDataType(datatype);
            thdq.setDataType(datatype);
            txl.setDataType(datatype);
            wechatFriendsxxEntity.setDataType(datatype);
            wechatLtjlEntity.setDataType(datatype);
            wechatZhxxEntity.setDataType(datatype);

//                System.out.println(jzxx.getName());
//        在脚本文件寻找dtree
            for (Element script : scripts) {
//            System.out.println("!!!");
                s = script.data();
                String a[] = s.split("\\n");
                for (String ai : a) {
                    us.jzxxParser(ai, jzxx,roleEntity, filePath, p, conn, name);
                    us.txlParser(ai, jzxx, txl, filePath, p, conn);
                    us.dxParser(ai, jzxx, dx, filePath, p, conn);
                    us.thqdParser(ai, jzxx, thdq, filePath, p, conn);
                    us.qqWechatParser(ai, jzxx, qqZhxxEntity, wechatZhxxEntity, filePath, p, conn);
                    us.qqFriendsParser(ai, jzxx, qqZhxxEntity, qqFriendsxxEntity, filePath, p, conn);
                    us.wechatFriendsParser(ai, jzxx, wechatFriendsxxEntity, wechatZhxxEntity, filePath, p, conn);
                    us.qqWechatLtjlParser(ai,jzxx, qqLtjlEntity, wechatLtjlEntity, filePath, p, conn);

                }
            }


        }catch (Exception e){
            File files = new File(uploadPath);
            String[] filep = files.list();
            File temps = null;
            for(int i=0;i<filep.length;i++){
                temps = new File(uploadPath+"/"+filep[i]);
                if(temps.isFile()){
                    temps.delete();
                }
            }
            new File(uploadPath).delete();
            e.getStackTrace();
        }finally {
            delFolder(uploadPath+"/"+ fname.split("/")[0]);
            delAllFile(uploadPath);
            new File(uploadPath).delete();
        }

//                conn.close();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        return mvc;
    }


    public static void delFolder(String folderPath){
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        File myFilePath=new File(filePath);
        myFilePath.delete();
    }

    public static void delAllFile(String path){
        File file = new File(path);
        if(file.exists()){
            String[] tempList = file.list();
            File temp = null;
            for(int i=0;i<tempList.length;i++){
                if(path.endsWith(File.separator)){
                    temp = new File(path+tempList[i]);
                }else{
                    temp = new File(path+File.separator+tempList[i]);
                }
                if(temp.isFile()){
                    temp.delete();
                }
                if(temp.isDirectory()){
                    delAllFile(path+"/"+tempList[i]);
                    delFolder(path+"/"+tempList[i]);
                }
            }
        }
    }
}
