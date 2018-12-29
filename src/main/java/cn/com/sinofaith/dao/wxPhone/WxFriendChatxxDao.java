package cn.com.sinofaith.dao.wxPhone;

import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.bean.TAutoWechatLtjlEntity;
import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.QqForm;
import cn.com.sinofaith.form.WxForm;
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
 * 微信好友聊天持久层
 */
@Repository
public class WxFriendChatxxDao extends BaseDao<TAutoWechatLtjlEntity> {


//    /**
//     * 条件查询总条数
//     * @param seach
//     * @param id
//     * @return
//     */
    /*public int getAllRowCounts(String seach, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) num from(select * from(select min(id) id,min(name) name,min(sfzhm) sfzhm,min(sjhm) sjhm,min(fswechatno) fswechatno,min(fswechatnc) fswechatnc,min(jswechatno) ");
        sql.append(" jswechatno,min(jsfriendnc) jsfriendnc,count(*) num from( select t.*,f.name,f.sfzhm,f.sjhm ");
        sql.append(" from (select * from (select t.*,row_number() over(partition by ");
        sql.append(" t.fswechatno,t.fswechatnc,t.fstime,t.jswechatno,t.jsfriendnc,t.fslx,t.lujing,t.data_type,t.u_name,t.u_number,t.aj_id ");
        sql.append(" order by t.id) su from T_AUTO_WECHAT_LTJL t) where su=1) t left join t_auto_wechat_friendsxx f ");
        sql.append(" on t.fswechatno=f.wechatno and t.jswechatno=f.fdwechatno where f.friendqh is null and t.aj_id="+id+") group by fswechatno,jswechatno)  t where 1=1 "+seach+" )");

        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        // 转成String
        BigDecimal num = (BigDecimal) map.get("NUM");
        return Integer.parseInt(num.toString());
    }*/

    public int getWxRowAll(String search) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) NUM from(");
        sql.append("select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH,q.ZHXX,q.QUNZHXX order by q.FSTIME ) su ");
        sql.append("from T_AUTO_wechat_LTJL q where 1=1 "+search+")t where su =1");
        List list = findBySQL(sql.toString());
        Map map = (Map) list.get(0);
        BigDecimal num = (BigDecimal) map.get("NUM");
        // 转成String
        return Integer.parseInt(num.toString());
    }

    public List<TAutoWechatLtjlEntity> getDoPageWx(int pageNo, int pageSize, String search) {
        Session session = getSession();
        List<TAutoWechatLtjlEntity> zhxxs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ( ");
        sql.append("SELECT c.*, ROWNUM rn FROM ( ");
        sql.append("select * from(");
        sql.append("select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH,q.ZHXX,q.QUNZHXX order by q.FSTIME ) su ");
        sql.append("from T_AUTO_WECHAT_LTJL q where 1=1 "+search+") t where su =1");
        sql.append(") c ");
        if((pageNo+pageSize)<pageNo){
            sql.append(" WHERE ROWNUM <= "+(pageNo)+") WHERE rn >= " + (pageNo+pageSize));
        }else {
            sql.append(" WHERE ROWNUM <= " + (pageNo + pageSize) + ") WHERE rn >= " + pageNo);
        }
        try {
            // 开启事务
            Transaction tx = session.beginTransaction();
            zhxxs = session.createSQLQuery(sql.toString())
                    .addEntity(TAutoWechatLtjlEntity.class).list();
            // 创建对象
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return zhxxs;
    }

    public int getAllRowCounts(String seach, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) num from (" +
                " select t.ZHXX,t.ZHNC,t.DSZH,t.DSNC,count(1)from(" +
                "  select t.*,row_number() over(partition by t.FSTIME,t.LUJING,t.DSZH order by t.FSTIME ) su " +
                "  from T_AUTO_WECHAT_LTJL t ");
        sql.append(" where t.QUNZHXX is null and t.AJ_ID= "+id+seach);
        sql.append(" ) t where su =1  group by t.ZHXX,t.ZHNC,t.DSZH,t.DSNC ) ");
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
    public List getDoPage(String seach, int currentPage, int pageSize, long id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( ");
        sql.append(" SELECT c.*, ROWNUM rn FROM ( ");
        sql.append(" select t.ZHXX,t.ZHNC,t.DSZH,t.DSNC,count(1) num from(" +
                "                 select t.*,row_number() over(partition by t.FSTIME,t.LUJING,t.DSZH order by t.FSTIME ) su " +
                "                  from T_AUTO_WECHAT_LTJL t ");
        sql.append(" where t.QUNZHXX is null and t.AJ_ID= "+id+seach);
        sql.append(" ) t where su =1  group by t.ZHXX,t.ZHNC,t.DSZH,t.DSNC order by num desc ) c ");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));

//        String sql1 = "select distinct wxh from t_auto_wechat_zhxx where aj_id="+id;
//        // 获得当前线程session
//        Session session = getSession();
//        List<TAutoWechatLtjlEntity> wxForms = null;
//        List wechat = null;
//        try{
//            // 开启事务
//            Transaction transaction = session.beginTransaction();
//            wxForms = session.createSQLQuery(sql.toString())
//                    .addEntity(TAutoWechatLtjlEntity.class).list();
//            wechat = session.createSQLQuery(sql1).list();
//            transaction.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            session.close();
//        }
//        String tempWechatno;
//        String tempWechatnc;
//        for (int i = 0; i < wxForms.size(); i++) {
//            for(int j=0;j<wechat.size();j++){
//                if(wxForms.get(i).getFswechatno().equals(wechat.get(j))) {
//                    wxForms.get(i).setFsfx("发送");
//                }else if(wxForms.get(i).getJswechatno().equals(wechat.get(j))){
//                    tempWechatno = wxForms.get(i).getFswechatno();
//                    wxForms.get(i).setFswechatno(wxForms.get(i).getJswechatno());
//                    wxForms.get(i).setJswechatno(tempWechatno);
//                    tempWechatnc = wxForms.get(i).getFswechatnc();
//                    wxForms.get(i).setFswechatnc(wxForms.get(i).getJsfriendnc());
//                    wxForms.get(i).setJsfriendnc(tempWechatnc);
//                    wxForms.get(i).setFsfx("接收");
//                }
//            }
//        }
        return findBySQL(sql.toString());
    }
    /*public List<WxForm> getDoPage(String seach, int currentPage, int pageSize, long id) {
        if(seach.contains(",t.id")){
            seach = " order by num,id ";
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( ");
        sql.append(" SELECT c.*, ROWNUM rn FROM ( ");
        sql.append("select * from (");
        sql.append(" select min(id) id,min(name) name,min(sfzhm) sfzhm,min(sjhm) sjhm,min(fswechatno) fswechatno,min(fswechatnc) fswechatnc,min(jswechatno) ");
        sql.append(" jswechatno,min(jsfriendnc) jsfriendnc,count(*) num from( select t.*,f.name,f.sfzhm,f.sjhm ");
        sql.append(" from (select * from (select t.*,row_number() over(partition by ");
        sql.append(" t.fswechatno,t.fswechatnc,t.fstime,t.jswechatno,t.jsfriendnc,t.fslx,t.lujing,t.data_type,t.u_name,t.u_number,t.aj_id ");
        sql.append(" order by t.id) su from T_AUTO_WECHAT_LTJL t) where su=1) t ");
        sql.append(" left join t_auto_wechat_friendsxx f  ");
        sql.append(" on t.fswechatno=f.wechatno and t.jswechatno=f.fdwechatno where f.friendqh is null and t.aj_id="+id+") group by fswechatno,jswechatno ) where 1=1 "+seach);
        sql.append(") c ");
        sql.append(" WHERE ROWNUM <= "+currentPage * pageSize+") WHERE rn >= " + ((currentPage - 1) * pageSize + 1));
        // 获得当前线程session
        Session session = getSession();
        SQLQuery query = null;
        List<WxForm> wxForms = null;
        try{
            // 开启事务
            Transaction transaction = session.beginTransaction();
            wxForms = session.createSQLQuery(sql.toString())
                    .addScalar("id", StandardBasicTypes.LONG)
                    .addScalar("name")
                    .addScalar("sfzhm")
                    .addScalar("sjhm")
                    .addScalar("fswechatno")
                    .addScalar("fswechatnc")
                    .addScalar("jswechatno")
                    .addScalar("jsfriendnc")
                    .addScalar("num",StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(WxForm.class)).list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return wxForms;
    }*/
}
