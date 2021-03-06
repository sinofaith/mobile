package cn.com.sinofaith.dao.phone;

import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.QqForm;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * qq群好友聊天持久层
 */
@Repository
public class FriendsChatxxDao extends BaseDao<TAutoQqLtjlEntity> {


    /**
     * 条件查询总条数
     * @param seach
     * @param id
     * @return
     */
    /*public int getAllRowCounts(String seach, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) num from(select * from( select min(id) id,min(name) name,min(sfzhm) sfzhm,min(sjhm) sjhm,min(fsqq) fsqq, ");
        sql.append(" min(fsqqnc) fsqqnc,min(jsqqno) jsqqno,min(jsqqnc) jsqqnc,count(*) num from(select t.*,f.name,f.sfzhm,f.sjhm ");
        sql.append(" from (select * from (select t.*,row_number() over(partition by t.fsqq,t.fsqqnc,t.fstime,t.jsqqno, ");
        sql.append(" t.jsqqnc,t.fslx,t.lujing,t.data_type,t.u_name,t.u_number,t.aj_id order by t.id) su from t_auto_qq_ltjl t) ");
        sql.append(" where su=1) t left join t_auto_qq_friendsxx f ");
        sql.append(" on t.fsqq=f.qq and t. jsqqno=f.fdqq where f.qqfriendqh is not null and t.aj_id="+id+") group by fsqq,jsqqno) t where 1=1 "+seach+")");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        // 转成String
        BigDecimal num = (BigDecimal) map.get("NUM");
        return Integer.parseInt(num.toString());
    }*/
    public int getAllRowCounts(String seach, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(1) num from ( " +
                "  select t.zhnc,t.zhxx,t.qunzhxx,count(1) num from( ");
        sql.append(" select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.qunzhxx order by q.FSTIME ) su  ");
        sql.append(" from T_AUTO_qq_LTJL q ) t ");
        sql.append(" where t.aj_id="+id+seach);
        sql.append(" GROUP BY t.zhxx,t.zhnc,t.QUNZHXX order by num desc ) ");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        // 转成String
        BigDecimal num = (BigDecimal) map.get("NUM");
        return Integer.parseInt(num.toString());
    }

    /**
     * 分页数据封装
     * @param seach
     * @param currentPage
     * @param pageSize
     * @param id
     * @return
     */
    /*public List<QqForm> getDoPage(String seach, int currentPage, int pageSize, long id) {
        if(seach.contains(",t.id")){
            seach = " order by num,id ";
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( ");
        sql.append(" SELECT c.*, ROWNUM rn FROM ( ");
        sql.append("select * from (");
        sql.append(" select min(id) id,min(name) name,min(sfzhm) sfzhm,min(sjhm) sjhm,min(fsqq) fsqq,min(fsqqnc) fsqqnc, ");
        sql.append(" min(jsqqno) jsqqno,min(jsqqnc) jsqqnc,min(qqfriendqh) qqfriendqh,count(*) num from(select t.*,f.name,f.sfzhm,f.sjhm,f.qqfriendqh ");
        sql.append(" from (select * from (select t.*,row_number() over(partition by ");
        sql.append(" t.fsqq,t.fsqqnc,t.fstime,t.jsqqno,t.jsqqnc,t.fslx,t.lujing,t.data_type,t.u_name,t.u_number,t.aj_id ");
        sql.append(" order by t.id) su from t_auto_qq_ltjl t) where su=1) t ");
        sql.append(" left join t_auto_qq_friendsxx f ");
        sql.append(" on t.fsqq=f.qq and t. jsqqno=f.fdqq where f.qqfriendqh is not null and t.aj_id="+id+") group by fsqq,jsqqno ) where 1=1 "+seach);
        sql.append(") c ");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));
        // 获得当前线程session
        Session session = getSession();
        SQLQuery query = null;
        List<QqForm> qqForms = null;
        try{
            // 开启事务
            Transaction transaction = session.beginTransaction();
            qqForms = session.createSQLQuery(sql.toString())
                    .addScalar("id", StandardBasicTypes.LONG)
                    .addScalar("name")
                    .addScalar("sfzhm")
                    .addScalar("sjhm")
                    .addScalar("fsqq")
                    .addScalar("fsqqnc")
                    .addScalar("jsqqno")
                    .addScalar("jsqqnc")
                    .addScalar("qqfriendqh")
                    .addScalar("num",StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(QqForm.class)).list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return qqForms;
    }*/
    public List<TAutoQqLtjlEntity>  getDoPage(String seach, int currentPage, int pageSize, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( ");
        sql.append(" SELECT c.*, ROWNUM rn FROM (");
        sql.append(" select t.zhxx,t.zhnc,t.qunzhxx,count(1) num from(select t.*,row_number() ");
        sql.append(" over(partition by t.FSTIME,t.LUJING,t.qunzhxx order by t.FSTIME ) su ");
        sql.append(" from T_AUTO_QQ_LTJL t where t.aj_id="+id+seach);
        sql.append(" order by fstime) t where su =1 group by t.zhxx,t.zhnc,t.qunzhxx  order by num desc  ");
        sql.append(" ) c");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));

        return findBySQL(sql.toString());
    }
}
