package cn.com.sinofaith.service.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatLtjlEntity;
import cn.com.sinofaith.dao.wxPhone.WxFriendChatxxDao;
import cn.com.sinofaith.form.QqForm;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.RemoveMessy;
import com.google.gson.Gson;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信好友聊天业务层
 */
@Service
public class WxFriendChatxxSerivce {

    @Autowired
    private WxFriendChatxxDao fcDao;
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
        seach.append(" and t.qunzhxx is null");
        // 当查询内容不为空时
        if(seachCode!=null) {
            seachCode = seachCode.replace("\r\n", "").replace("，", "").replace(" ", "").replace(" ", "").replace("\t", "");
            if(seachCondition.equals("jswechatno")){
                seach.append(" and (" + seachCondition + " like '%" + seachCode + "%' or fswechatno like '%"+seachCode+"%')");
            }else if(seachCondition.equals("jsfriendnc")){
                seach.append(" and (" + seachCondition + " like '%" + seachCode + "%' or fswechatnc like '%"+seachCode+"%')");
            }else{
                seach.append(" and " + seachCondition + " like '%" + seachCode + "%'");
            }
        }
        if(orderby!=null){
            seach .append(" order by "+orderby).append(desc);
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
        List<QqForm> qqFs = new ArrayList<>();
        QqForm qqf = new QqForm();
        int allRow = fcDao.getAllRowCounts(seach,id);
        if(allRow>0){
            List list  = fcDao.getDoPage(seach, currentPage, pageSize, id);
            for (int i = 0; i <list.size(); i++) {
//                wxForms.get(i).setFswechatnc((wxForms.get(i).getFswechatnc()));
                qqf=qqf.wxmapToForm((Map) list.get(i));
                qqf.setId((currentPage-1)*pageSize+i+1);
                qqFs.add(qqf);
            }
            // 封装page
            page.setPageNo(currentPage);
            page.setList(qqFs);
            page.setTotalRecords(allRow);
            page.setPageSize(pageSize);
        }
        return page;
    }

    /**
     * 聊天详细信息
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public String getFriendChat(int currentPage, int pageSize, DetachedCriteria dc) {
        Gson gson = new Gson();
        Page page = new Page();
        List<TAutoWechatLtjlEntity> ltjls = null;
        int rowAll = fcDao.getRowAll(dc);
        if(rowAll>0){
            ltjls = fcDao.getDoPage(currentPage, pageSize, dc);
            for (int i = 0; i <ltjls.size(); i++) {
                ltjls.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            page.setPageSize(pageSize);
            page.setTotalRecords(rowAll);
            page.setList(ltjls);
            page.setPageNo(currentPage);
            return gson.toJson(page);
        }
        return null;
    }

    public Page getFriendChat(int currentPage, int pageSize, String search) {
        Page page = new Page();
        int rowAll = fcDao.getWxRowAll(search);
        if(rowAll>0){
            List<TAutoWechatLtjlEntity> ltjls = fcDao.getDoPageWx(currentPage,pageSize,search);
            page.setPageSize(pageSize);
            page.setList(ltjls);
            page.setTotalRecords(rowAll);
            page.setPageNo(currentPage);
        }
        return page;
    }
}
