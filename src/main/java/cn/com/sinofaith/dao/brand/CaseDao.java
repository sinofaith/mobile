package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CaseDao extends BaseDao<CaseEntity>{

    public int getAllRowCount(String seachCode) {
        String sql = "select to_char(count(c.case_id)) num from t_case c " +
                " left join t_region r on c.case_id = r.case_id  " +
                " left join t_area a on r.area_id=a.id where 1=1 " + seachCode;
        List list = findBySQL(sql);
        Map map = (Map) list.get(0);
        return Integer.parseInt((String) map.get("NUM"));
    }


    public List getDoPage(String seach, int offset, int length) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM (SELECT a.*, ROWNUM rn ");
        sql.append("FROM (select c.*,a.name,r.inserttime as r_inserttime from t_case c ");
        sql.append("left join t_region r on c.case_id = r.case_id ");
        sql.append("left join t_area a on r.area_id=a.id ");
        sql.append(" where 1=1 " + seach + " ) a ");
        sql.append("WHERE ROWNUM <= " + offset * length + ") WHERE rn >= " + ((offset - 1) * length + 1));
        return findBySQL(sql.toString());
    }

    public int getRegionId(String caseName, String regionName) {
        StringBuffer sql = new StringBuffer();
        sql.append("select to_char(r.region_id) region_id from t_case c left join t_region r on c.case_id = r.case_id ");
        sql.append("left join t_area a on r.area_id = a.id where c.case_name = '"+caseName+"' and a.name = '"+regionName+"'");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        return Integer.parseInt((String) map.get("REGION_ID"));
    }
}
