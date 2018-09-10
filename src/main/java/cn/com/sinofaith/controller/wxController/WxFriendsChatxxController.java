package cn.com.sinofaith.controller.wxController;

import cn.com.sinofaith.bean.AjEntity;
import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.wxPhone.WxFriendsChatxxSerivce;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

/**
 * 微信群好友聊天控制器
 */
@Controller
@RequestMapping("/wxFriendsChat")
public class WxFriendsChatxxController {

    @Autowired
    private WxFriendsChatxxSerivce fcService;

    /**
     * 重定向到分页请求，将session域中的条件数据清空
     *
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView qqFriendChat(HttpSession session, String flag) {
        ModelAndView mav = new ModelAndView("redirect:/wxFriendsChat/seach?pageNo=1");
        session.removeAttribute("wxFriendsChatxxSeachCode"); //查询条件
        session.removeAttribute("wxFriendsChatxxSeachCondition");//查询内容
        session.removeAttribute("wxFriendsChatOrder");
        session.removeAttribute("wxFriendsChatlastOrder");
        session.removeAttribute("wxFriendsChatDesc");
        session.setAttribute("flag", flag);
        return mav;
    }

    /**
     * 分页显示数据
     *
     * @param pageNo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/seach")
    public String seach(String pageNo, HttpSession session, Model model) {
        // 将session域中的数据取出
        // 查询内容
        String seachCode = (String) session.getAttribute("wxFriendsChatxxSeachCode");
        // 查询字段
        String seachCondition = (String) session.getAttribute("wxFriendsChatxxSeachCondition");
        // 排序字段
        String orderby = (String) session.getAttribute("wxFriendsChatOrder");
        // 排序方式(desc降，asc升)
        String desc = (String) session.getAttribute("wxFriendsChatDesc");
        // 所属案件
        AjEntity aj = (AjEntity) session.getAttribute("aj");

        //------测试环境下-------
        if (aj == null) {
            aj = new AjEntity();
            aj.setId(1);
        }
        //----------------------

        // 封装sql语句
        String seach = fcService.getSeach(seachCondition, seachCode, orderby, desc);
        // 封装分页对象
        Page page = fcService.queryForPage(parseInt(pageNo), 4, seach, aj.getId());
        if (page != null) {
            model.addAttribute("page", page);
            model.addAttribute("detailinfo", page.getList());
        }
        model.addAttribute("phone", "wxFriendsChat");
        return "phone/phoneWXfriendsChat";
    }

    /**
     * 根据字段名的seachCode进行(模糊)查询
     *
     * @param seachCondition
     * @param seachCode
     * @param session
     * @return
     */
    @RequestMapping(value = "/seachCode", method = RequestMethod.POST)
    public String seachCode(String seachCondition, String seachCode, HttpSession session) {
        if (seachCode == null || seachCode.isEmpty()) {
            session.removeAttribute("wxFriendsChatxxSeachCode");
            session.removeAttribute("wxFriendsChatxxSeachCondition");
            return "redirect:/wxFriendsChat/seach?pageNo=1";
        }
        session.setAttribute("wxFriendsChatxxSeachCode", seachCode);
        session.setAttribute("wxFriendsChatxxSeachCondition", seachCondition);
        return "redirect:/wxFriendsChat/seach?pageNo=1";
    }

    /**
     * 根据orderby字段升序或降序
     *
     * @param orderby
     * @param session
     * @return
     */
    @RequestMapping("/order")
    public String seachCode(String orderby, HttpSession session) {
        // 取出session域中friendChatDesc和friendChatlastOrder
        String desc = (String) session.getAttribute("wxFriendsChatDesc");
        String lastOrder = (String) session.getAttribute("wxFriendsChatlastOrder");
        // 当不是首次点击的时候
        if (orderby.equals(lastOrder)) {
            if (desc == null || " ,t.id ".equals(desc)) {
                desc = " desc ";
            } else {
                desc = " ,t.id ";
            }
        } else {
            desc = " desc ";
        }
        // 将数据存入session中
        session.setAttribute("wxFriendsChatDesc", desc);
        session.setAttribute("wxFriendsChatlastOrder", orderby);
        session.setAttribute("wxFriendsChatOrder", orderby);
        return "redirect:/wxFriendsChat/seach?pageNo=1";
    }

    /**
     * 好友聊天信息
     * @param fswechatno
     * @param jswechatno
     * @param page
     * @param order
     * @param session
     * @return
     */
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDetails(String fswechatno, String jswechatno, int page, String order, HttpSession session) {
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoQqLtjlEntity.class);
        dc.add(Restrictions.eq("fswechatno",fswechatno));
        dc.add(Restrictions.eq("jswechatno",jswechatno));
        // 从session域中取出数据
        AjEntity aj = (AjEntity) session.getAttribute("aj");
        String lastOrder = (String) session.getAttribute("xqlastOrder");
        String desc = (String) session.getAttribute("xqdesc");

        //------测试环境--------
        if(aj==null){
           aj = new AjEntity();
           aj.setId(1);
        }
        //---------------------

        dc.add(Restrictions.eq("aj_id",aj.getId()));
        if(order.equals(lastOrder)){
           if(desc==null || desc.equals("desc")){
               dc.addOrder(Order.desc(order));
               desc = "";
           }else{
               dc.addOrder(Order.asc(order));
               desc = "desc";
           }
        }else{
            dc.addOrder(Order.asc(order));
            desc = "desc";
        }
        session.setAttribute("xqdesc", desc);
        session.setAttribute("xqlastOrder", order);
        String json = fcService.getFriendChat(page, 100, dc);
        return json;
    }

}