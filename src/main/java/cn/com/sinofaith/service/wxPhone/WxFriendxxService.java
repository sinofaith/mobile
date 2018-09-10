package cn.com.sinofaith.service.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatFriendsxxEntity;
import cn.com.sinofaith.dao.wxPhone.WxFriendxxDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信好友信息业务层
 */
@Service
public class WxFriendxxService {

    @Autowired
    private WxFriendxxDao friendxxDao;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, DetachedCriteria dc) {
        // 分页对象
        Page page = new Page();
        // 获取总条数
        int rowAll = friendxxDao.getRowAll(dc);
        // 封装wuliu_relation表
        List<TAutoWechatFriendsxxEntity> zhxxs = null;
        if (rowAll>0) {
            zhxxs = friendxxDao.getDoPage(currentPage,pageSize,dc);
            // 封装page
            page.setPageSize(pageSize);
            page.setTotalRecords(rowAll);
            page.setList(zhxxs);
            page.setPageNo(currentPage);
            return page;
        }
        return page;
    }
}
