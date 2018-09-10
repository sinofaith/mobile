package cn.com.sinofaith.dao.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatLtjlEntity;
import cn.com.sinofaith.dao.BaseDao;
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


    /**
     * 条件查询总条数
     * @param seach
     * @param id
     * @return
     */
    public int getAllRowCounts(String seach, long id) {
//        String sql = "select count(*) num from T_AUTO_WECHAT_LTJL t left join t_auto_wechat_friendsxx f on " +
//                " t.fswechatno=f.wechatno and t.jswechatno=f.fdwechatno  where f.friendqh is null and t.aj_id="+id+seach;
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
    }

    /**
     * 分页数据封装
     * @param seach
     * @param currentPage
     * @param pageSize
     * @param id
     * @return
     */
    public List<WxForm> getDoPage(String seach, int currentPage, int pageSize, long id) {
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
    }
}
