package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CaseDao extends BaseDao<CaseEntity>{

    public int getAllRowCount(String seachCode) {
        String sql = "select to_char(count(c.id)) num from t_case c " +
                " left join t_brand b on c.brand_id = b.id " +
                " left join t_region r on c.region_id = r.id where 1=1 " + seachCode;
        List list = findBySQL(sql);
        Map map = (Map) list.get(0);
        return Integer.parseInt((String) map.get("NUM"));
    }


    public List getDoPage(String seach, int offset, int length) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM (SELECT a.*, ROWNUM rn ");
        sql.append("FROM (select c.id,c.casename,c.inserttime,b.brandname,r.regionname from t_case c ");
        sql.append("left join t_brand b on c.brand_id = b.id ");
        sql.append("left join t_region r on c.region_id = r.id where 1=1 " + seach + ") a ");
        sql.append("WHERE ROWNUM <= " + offset * length + ") WHERE rn >= " + ((offset - 1) * length + 1));
        return findBySQL(sql.toString());
    }
}
