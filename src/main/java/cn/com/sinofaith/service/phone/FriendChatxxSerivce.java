package cn.com.sinofaith.service.phone;

import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.dao.phone.FriendChatxxDao;
import cn.com.sinofaith.form.QqForm;
import cn.com.sinofaith.form.WxForm;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.RemoveMessy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.taglibs.standard.tag.rt.core.ForEachTag;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * qq好友聊天业务层
 */
@Service
public class FriendChatxxSerivce {

    @Autowired
    private FriendChatxxDao fcDao;
    /**
     * 封装sql语句
     * @param seachCondition
     * @param seachCode
     * @param orderby
     * @param desc
     * @return
     */
    public String getSeach(String seachCondition, String seachCode, String orderby, String desc) {
        StringBuffer seach = new StringBuffer();
        // 当查询内容不为空时
        if(seachCode!=null) {
            seachCode = seachCode.replace("\r\n", "").replace("，", "").replace(" ", "").replace(" ", "").replace("\t", "");
            if(seachCondition.equals("fsqq")){
                seach.append(" and (" + seachCondition + " like '%" + seachCode + "%'");
            }else if(seachCondition.equals("fsqqnc")){
                seach.append(" and (" + seachCondition + " like '%" + seachCode + "%'");
            }else{
                seach.append(" and " + seachCondition + " like '%" + seachCode + "%'");
            }
        }
        seach.append(" group by t.dsnc,t.dszh,t.zhnc,t.zhxx,t.u_name");
        if(orderby!=null){
            seach.append(" order by "+orderby).append(desc);
        }else{
            seach.append(" order by num desc");
        }

        return seach.toString();
    }

    /**
     * 封装分页对象
     * @param currentPage
     * @param pageSize
     * @param seach
     * @param id
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, String seach, long id) {
        Page page = new Page();
        // 封装wuliu_relation表
        List<QqForm> qqForms = null;
        int allRow = fcDao.getAllRowCounts(seach,id);
        if(allRow>0){
            qqForms = fcDao.getDoPage(seach, currentPage, pageSize, id);
            for (int i = 0; i <qqForms.size(); i++) {
                qqForms.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            // 封装page
            page.setPageNo(currentPage);
            page.setList(qqForms);
            page.setTotalRecords(allRow);
            page.setPageSize(pageSize);
        }
        return page;
    }

    /**
     * 聊天详细信息
     * @return
     */
    public Page getFriendChat(int currentPage, int pageSize, String search) {
        Page page = new Page();
        int rowAll = fcDao.getQQRowAll(search);
        if(rowAll>0){
            List<TAutoQqLtjlEntity> ltjls = fcDao.getDoPageQQ(currentPage,pageSize,search);
            page.setPageSize(pageSize);
            page.setList(ltjls);
            page.setTotalRecords(rowAll);
            page.setPageNo(currentPage);
        }
        return page;
    }

    public String  getChatByFilter(String seach,String content){
        Gson gson = new GsonBuilder().serializeNulls().create();
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from ( SELECT c.*, ROWNUM rn FROM ( ");
        sql.append(" select * from(select q.*,row_number() over(partition by q.FSTIME,q.LUJING,q.DSZH,q.ZHXX,q.QUNZHXX order by q.FSTIME ) su ");
        sql.append(" from T_AUTO_QQ_LTJL q where 1=1 "+seach);
        sql.append(" ) t where su =1) c ) where lujing like '%"+content+"%' and fslx = '文字'" );
        List list = fcDao.findBySQL(sql.toString());
        List<WxForm> wxs = new ArrayList<>();
        WxForm wx = new WxForm();
        for (int i = 0; i < list.size(); i++) {
            wx = wx.wxsmapToForm((Map) list.get(i));
            wxs.add(wx);
        }
        return gson.toJson(wxs);
    }
}
