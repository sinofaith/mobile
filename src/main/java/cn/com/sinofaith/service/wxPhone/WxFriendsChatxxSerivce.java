package cn.com.sinofaith.service.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatLtjlEntity;
import cn.com.sinofaith.dao.phone.FriendsChatxxDao;
import cn.com.sinofaith.dao.wxPhone.WxFriendsChatxxDao;
import cn.com.sinofaith.form.WxForm;
import cn.com.sinofaith.page.Page;
import com.google.gson.Gson;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信群好友聊天业务层
 */
@Service
public class WxFriendsChatxxSerivce {

    @Autowired
    private WxFriendsChatxxDao fcDao;
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
            seach.append(" and " + seachCondition + " like '" + seachCode + "'");
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
        // 封装wuliu_relation表
        List<TAutoWechatLtjlEntity> wxForms = null;
        int allRow = fcDao.getAllRowCounts(seach,id);
        if(allRow>0){
            wxForms = fcDao.getDoPage(seach, currentPage, pageSize, id);
            // 将字节转成字符串
            for(TAutoWechatLtjlEntity ltjl : wxForms){
                ltjl.setFanrs(new String(ltjl.getFanr()));
            }
            for (int i = 0; i <wxForms.size(); i++) {
                wxForms.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            // 封装page
            page.setPageNo(currentPage);
            page.setList(wxForms);
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
            // 将字节转成字符串
            for(TAutoWechatLtjlEntity ltjl : ltjls){
                ltjl.setFanrs(new String(ltjl.getFanr()));
            }
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
}