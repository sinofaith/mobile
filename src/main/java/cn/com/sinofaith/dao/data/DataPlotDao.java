package cn.com.sinofaith.dao.data;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.AnnualDataForm;
import cn.com.sinofaith.form.PlotForm;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DataPlotDao extends BaseDao<BrandEntity>{

    /**
     * 获取绘图数据
     * @return
     */
    public List<PlotForm> getPlotForm() {
        String sql = "select unit_name name,count(1) value from T_BRAND group by unit_name";
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql)
                    .addScalar("name")
                    .addScalar("value", StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }

    /**
     * 地图数据
     * @return
     */
    public List<PlotForm> getPlotMapForm() {
        StringBuffer sql = new StringBuffer();
        sql.append("select b.brand_name,c.case_name,a1.name,wmsys.wm_concat(a.name) area,count(1) value from t_brand b");
        sql.append(" inner join t_case c on b.brand_id = c.brand_id left join t_region r on c.case_id = r.case_id");
        sql.append(" left join t_area a on r.area_id = a.id left join t_area a1 on a1.id = a.pid");
        sql.append(" group by b.brand_name,c.case_name,a1.name order by b.brand_name");
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql.toString())
                    .addScalar("brand_name")
                    .addScalar("case_name")
                    .addScalar("name")
                    .addScalar("area")
                    .addScalar("value", StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }

    public List<PlotForm> getPlotBrandForm() {
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct(b.brand_name) from t_brand b inner join t_case c on b.brand_id = c.brand_id ");
        sql.append(" left join t_region r on c.case_id = r. case_id left join t_area a on r.area_id = a.id");
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql.toString())
                    .addScalar("brand_name")
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }

    /**
     * 年度数据获取
     * @return
     */
    public Map<String,List<AnnualDataForm>> getMapAnnualData() {
        Map<String,List<AnnualDataForm>> annualDataForm = new HashMap<>();
        // 书写sql语句
        String sql1 = "select substr(trim(inserttime),1,4) year ,count(1) num " +
                "from t_brand group by substr(trim(inserttime),1,4) order by substr(trim(inserttime),1,4) desc";
        String sql2 = "select substr(trim(inserttime),1,4) year ,count(1) num " +
                "from t_case group by substr(trim(inserttime),1,4) order by substr(trim(inserttime),1,4) desc";
        String sql3 = "select substr(trim(inserttime),1,4) year ,count(1) num " +
                "from t_region group by substr(trim(inserttime),1,4) order by substr(trim(inserttime),1,4) desc";
        String sql4 = "select substr(trim(inserttime),1,4) year ,count(1) num " +
                "from t_role group by substr(trim(inserttime),1,4) order by substr(trim(inserttime),1,4) desc";
        List<AnnualDataForm> annualDatas = new ArrayList<>();
        // 获得当前线程绑定的session
        Session session = getSession();
        try {
            // 开启事务
            Transaction tx = session.beginTransaction();
            for(int i=0;i<4;i++){
                SQLQuery query = null;
                if(i==0){
                    query = session.createSQLQuery(sql1)
                            .addScalar("year")
                            .addScalar("num", StandardBasicTypes.LONG);
                }else if(i==1){
                    query = session.createSQLQuery(sql2)
                            .addScalar("year")
                            .addScalar("num", StandardBasicTypes.LONG);
                }else if(i==2){
                    query = session.createSQLQuery(sql3)
                            .addScalar("year")
                            .addScalar("num", StandardBasicTypes.LONG);
                }else if(i==3){
                    query = session.createSQLQuery(sql4)
                            .addScalar("year")
                            .addScalar("num", StandardBasicTypes.LONG);
                }
                // 设置只获取前四条
                query.setFirstResult(0);
                query.setMaxResults(4);
                annualDatas = query.setResultTransformer(Transformers.aliasToBean(AnnualDataForm.class)).list();
                annualDataForm.put(i+"", annualDatas);
                // 关闭事务
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return annualDataForm;
    }
}
