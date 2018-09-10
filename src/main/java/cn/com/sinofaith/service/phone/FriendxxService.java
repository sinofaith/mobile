package cn.com.sinofaith.service.phone;

import cn.com.sinofaith.bean.TAutoQqFriendsxxEntity;
import cn.com.sinofaith.dao.phone.FriendxxDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * qq好友信息业务层
 */
@Service
public class FriendxxService {

    @Autowired
    private FriendxxDao friendxxDao;

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
        List<TAutoQqFriendsxxEntity> zhxxs = null;
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
