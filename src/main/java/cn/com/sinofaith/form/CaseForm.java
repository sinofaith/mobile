package cn.com.sinofaith.form;

import java.util.Map;

public class CaseForm {
    private long xh;
    private String id;
    private String caseName;
    private String brandName;
    private String regionName;
    private String inserttime;

    public long getXh() {
        return xh;
    }

    public void setXh(long xh) {
        this.xh = xh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }



    public CaseForm mapToForm(Map map){
        CaseForm zzf = new CaseForm();
        zzf.setId(map.get("ID").toString());
        zzf.setCaseName((String) map.get("CASENAME"));
        zzf.setBrandName((String)map.get("BRANDNAME"));
        zzf.setRegionName((String)map.get("REGIONNAME"));
        zzf.setInserttime((String)map.get("INSERTTIME"));
        return zzf;
    }
}
