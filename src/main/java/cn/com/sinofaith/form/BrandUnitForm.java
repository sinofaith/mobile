package cn.com.sinofaith.form;

import java.math.BigDecimal;
import java.util.Map;

public class BrandUnitForm {
    private long xh;
    private BigDecimal brandId;
    private String brandName;
    private String unitName;
    private String inserttime;

    public long getXh() {
        return xh;
    }

    public void setXh(long xh) {
        this.xh = xh;
    }

    public BigDecimal getBrandId() {
        return brandId;
    }

    public void setBrandId(BigDecimal brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public BrandUnitForm mapToForm(Map map){
        BrandUnitForm bf = new BrandUnitForm();
        bf.setBrandId((BigDecimal)map.get("BRAND_ID"));
        bf.setBrandName((String)map.get("BRAND_NAME"));
        bf.setUnitName((String)map.get("UNIT_NAME"));
        bf.setInserttime((String) map.get("INSERTTIME"));
        return bf;
    }
}
