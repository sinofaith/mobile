package cn.com.sinofaith.util;

public class RemoveMessy {
    public static String rMessy(String str_VarMboxRead) {
        if(str_VarMboxRead!=null) {
            StringBuffer str_Result = new StringBuffer();
            String str_OneStr = "";
            for (int z = 0; z < str_VarMboxRead.length(); z++) {
                str_OneStr = str_VarMboxRead.substring(z, z + 1);
                if (str_OneStr.matches("[\\x00-\\x7F]+") || str_OneStr.matches("[\u4e00-\u9fa5]+")) {
                    str_Result = str_Result.append(str_OneStr);
                }
            }
            return str_Result.toString();
        }else {
            return "";
        }
    }

    public static void main(String[] args) {
        String s = rMessy("①张？大？？？？？？苏打");
        System.out.println(s);
    }
}
