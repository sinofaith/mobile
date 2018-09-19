package cn.com.sinofaith.form;

import java.math.BigDecimal;
import java.util.Map;

public class CaseForm {
    private long xh;
    private BigDecimal id;
    private String caseName;
    private String creater;
    private String inserttime;

    public long getXh() {
        return xh;
    }

    public void setXh(long xh) {
        this.xh = xh;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public CaseForm mapToForm(Map map){
        CaseForm zzf = new CaseForm();
        zzf.setId((BigDecimal)map.get("CASE_ID"));
        zzf.setCaseName((String) map.get("CASE_NAME"));
        zzf.setCreater((String)map.get("CREATER"));
        zzf.setInserttime((String)map.get("INSERTTIME"));
        return zzf;
    }
}
