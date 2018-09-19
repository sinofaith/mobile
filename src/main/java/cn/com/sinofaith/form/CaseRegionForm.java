package cn.com.sinofaith.form;

import java.math.BigDecimal;
import java.util.Map;

public class CaseRegionForm {
    private long xh;
    private BigDecimal regionId;
    private String regionName;
    private String roleName;
    private String inserttime;

    public long getXh() {
        return xh;
    }

    public void setXh(long xh) {
        this.xh = xh;
    }

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public CaseRegionForm mapToForm(Map map){
        CaseRegionForm bf = new CaseRegionForm();
        bf.setRegionId((BigDecimal) map.get("REGION_ID"));
        bf.setRegionName((String)map.get("REGION_NAME"));
        bf.setRoleName((String)map.get("ROLE_NAME"));
        bf.setInserttime((String) map.get("INSERTTIME"));
        return bf;
    }
}
