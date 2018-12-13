package cn.com.sinofaith.dao.phone;

import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.QqForm;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * qq好友聊天持久层
 */
@Repository
public class FriendChatxxDao extends BaseDao<TAutoQqLtjlEntity> {

    public int getAllRowCounts(String seach, long id) {
        // 发送方
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) NUM from(");
        sql.append("select t.zhnc,t.zhxx,t.dsnc,t.dszh,t.u_name,count(1) num from(");
        sql.append("select c.*,row_number() over(partition by c.FSTIME,c.LUJING,c.DSZH order by c.FSTIME ) su ");
        sql.append("from T_AUTO_QQ_LTJL c where QUNZHXX is null and lujing is not null and AJ_ID ="+id+")t where su =1");
        sql.append(seach+")");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        BigDecimal num = (BigDecimal) map.get("NUM");
        // 转成String
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
    public List<QqForm> getDoPage(String seach, int currentPage, int pageSize, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ( ");
        sql.append("SELECT c.*, ROWNUM rn FROM ( ");
        sql.append("select t.zhnc,t.zhxx,t.dsnc,t.dszh,t.u_name,count(1) num from(");
        sql.append("select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH order by q.FSTIME ) su ");
        sql.append("from T_AUTO_QQ_LTJL q where QUNZHXX is null and lujing is not null and AJ_ID ="+id+")t where su =1 "+seach);
        sql.append(") c ");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));

        // 获得当前线程session
        Session session = getSession();
        List<QqForm> qqForms = null;
        List qq = null;
        try{
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 发送方数据
            qqForms = session.createSQLQuery(sql.toString())
                    .addScalar("zhnc")
                    .addScalar("zhxx")
                    .addScalar("dsnc")
                    .addScalar("dszh")
                    .addScalar("u_name")
                    .addScalar("num", StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(QqForm.class)).list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return qqForms;
    }

    /**
     * 详情总条数
     * @param search
     * @return
     */
    public int getQQRowAll(String search) {
        Session session = getSession();
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) NUM from(");
        sql.append("select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH order by q.FSTIME ) su ");
        sql.append("from T_AUTO_QQ_LTJL q where "+search+")t where su =1");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        BigDecimal num = (BigDecimal) map.get("NUM");
        // 转成String
        return Integer.parseInt(num.toString());
    }

    public List<TAutoQqLtjlEntity> getDoPageQQ(int currentPage, int pageSize, String search) {
        Session session = getSession();
        List<TAutoQqLtjlEntity> zhxxs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ( ");
        sql.append("SELECT c.*, ROWNUM rn FROM ( ");
        sql.append("select * from(");
        sql.append("select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH order by q.FSTIME ) su ");
        sql.append("from T_AUTO_QQ_LTJL q where"+search+")t where su =1");
        sql.append(") c ");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));
        try {
            // 开启事务
            Transaction tx = session.beginTransaction();
            zhxxs = session.createSQLQuery(sql.toString())
                    .addEntity(TAutoQqLtjlEntity.class).list();
            // 创建对象
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return zhxxs;
    }
}
