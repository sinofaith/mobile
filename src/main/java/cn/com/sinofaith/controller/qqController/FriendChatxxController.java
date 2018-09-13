package cn.com.sinofaith.controller.qqController;

import cn.com.sinofaith.bean.AjEntity;
import cn.com.sinofaith.bean.TAutoQqLtjlEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.phone.FriendChatxxSerivce;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * qq好友聊天控制器
 */
@Controller
@RequestMapping("/phoneqqFriendChat")
public class FriendChatxxController {

    @Autowired
    private FriendChatxxSerivce fcService;

    /**
     * 重定向到分页请求，将session域中的条件数据清空
     *
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView qqFriendChat(HttpSession session, String flag) {
        ModelAndView mav = new ModelAndView("redirect:/phoneqqFriendChat/seach?pageNo=1");
        session.removeAttribute("friendChatxxSeachCode"); //查询条件
        session.removeAttribute("friendChatxxSeachCondition");//查询内容
        session.removeAttribute("friendChatOrder");
        session.removeAttribute("friendChatlastOrder");
        session.removeAttribute("friendChatDesc");
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
        String seachCode = (String) session.getAttribute("friendChatxxSeachCode");
        // 查询字段
        String seachCondition = (String) session.getAttribute("friendChatxxSeachCondition");
        // 排序字段
        String orderby = (String) session.getAttribute("friendChatOrder");
        // 排序方式(desc降，asc升)
        String desc = (String) session.getAttribute("friendChatDesc");
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
        model.addAttribute("phone", "phoneqqFriendChat");
        return "phone/phonefriendChat";
    }

    /**
     * 根据字段名的seachCode进行(模糊)查询
     * @param seachCondition
     * @param seachCode
     * @param session
     * @return
     */
    @RequestMapping(value = "/seachCode", method = RequestMethod.POST)
    public String seachCode(String seachCondition, String seachCode, HttpSession session) {
        if (seachCode == null || seachCode.isEmpty()) {
            session.removeAttribute("friendChatxxSeachCode");
            session.removeAttribute("friendChatxxSeachCondition");
            return "redirect:/phoneqqFriendChat/seach?pageNo=1";
        }
        session.setAttribute("friendChatxxSeachCode", seachCode);
        session.setAttribute("friendChatxxSeachCondition", seachCondition);
        return "redirect:/phoneqqFriendChat/seach?pageNo=1";
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
        String desc = (String) session.getAttribute("friendChatDesc");
        String lastOrder = (String) session.getAttribute("friendChatlastOrder");
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
        session.setAttribute("friendChatDesc", desc);
        session.setAttribute("friendChatlastOrder", orderby);
        session.setAttribute("friendChatOrder", orderby);
        return "redirect:/phoneqqFriendChat/seach?pageNo=1";
    }

    /**
     * 好友聊天信息
     * @param fsqq
     * @param jsqq
     * @param page
     * @param order
     * @param session
     * @return
     */
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDetails(String fsqq, String jsqq, int page, String order, HttpSession session) {
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoQqLtjlEntity.class);
        dc.add(Restrictions.eq("fsqq",fsqq));
        dc.add(Restrictions.eq("jsqqno",jsqq));
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