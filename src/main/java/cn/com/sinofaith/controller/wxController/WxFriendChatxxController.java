package cn.com.sinofaith.controller.wxController;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoWechatLtjlEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.wxPhone.WxFriendChatxxSerivce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * 微信好友聊天控制器
 */
@Controller
@RequestMapping("/phonewxFriendChat")
public class WxFriendChatxxController {

    @Autowired
    private WxFriendChatxxSerivce wfcService;

    /**
     * 重定向到分页请求，将session域中的条件数据清空
     *
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView wxFriendChat(HttpSession session, String flag) {
        ModelAndView mav = new ModelAndView("redirect:/phonewxFriendChat/seach?pageNo=1");
        session.removeAttribute("wxFriendChatxxSeachCode"); //查询条件
        session.removeAttribute("wxFriendChatxxSeachCondition");//查询内容
        session.removeAttribute("wxFriendChatOrder");
        session.removeAttribute("wxFriendChatlastOrder");
        session.removeAttribute("wxFriendChatDesc");
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
        String seachCode = (String) session.getAttribute("wxFriendChatxxSeachCode");
        // 查询字段
        String seachCondition = (String) session.getAttribute("wxFriendChatxxSeachCondition");
        // 排序字段
        String orderby = (String) session.getAttribute("wxFriendChatOrder");
        // 排序方式(desc降，asc升)
        String desc = (String) session.getAttribute("wxFriendChatDesc");
        // 所属案件
        Long aj_id = (Long) session.getAttribute("aj_id");
        if(aj_id==null || aj_id==0){
            return "phone/phoneWXfriendChat";
        }

        // 封装sql语句
        String seach = wfcService.getSeach(seachCondition, seachCode, orderby, desc);
        // 封装分页对象
        Page page = wfcService.queryForPage(parseInt(pageNo), 10, seach, aj_id);
        if (page != null) {
            model.addAttribute("page", page);
            model.addAttribute("detailinfo", page.getList());
        }
        model.addAttribute("phone", "phonewxFriendChat");
        return "phone/phoneWXfriendChat";
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
            session.removeAttribute("wxFriendChatxxSeachCode");
            session.removeAttribute("wxFriendChatxxSeachCondition");
            return "redirect:/phonewxFriendChat/seach?pageNo=1";
        }
        session.setAttribute("wxFriendChatxxSeachCode", seachCode);
        session.setAttribute("wxFriendChatxxSeachCondition", seachCondition);
        return "redirect:/phonewxFriendChat/seach?pageNo=1";
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
        String desc = (String) session.getAttribute("wxFriendChatDesc");
        String lastOrder = (String) session.getAttribute("wxFriendChatlastOrder");
        // 当不是首次点击的时候
        if (orderby.equals(lastOrder)) {
            if (desc == null || " ".equals(desc)) {
                desc = " desc nulls last,id desc ";
            } else {
                desc = " ";
            }
        } else {
            desc = " desc nulls last,id desc ";
        }
        // 将数据存入session中
        session.setAttribute("wxFriendChatDesc", desc);
        session.setAttribute("wxFriendChatlastOrder", orderby);
        session.setAttribute("wxFriendChatOrder", orderby);
        return "redirect:/phonewxFriendChat/seach?pageNo=1";
    }

    @RequestMapping(value = "/getDetails",  method = RequestMethod.POST)
    public @ResponseBody String getDetails(String zhxx, String dszh, int pageNo, int pageSize, HttpSession session) {
        Long aj_id = (Long) session.getAttribute("aj_id");
        StringBuffer seach = new StringBuffer();
        seach.append(" and aj_id = " + aj_id);
        seach.append( " and zhxx = '" + zhxx + "'");
        if(dszh.contains("@")){
            seach.append(" and qunzhxx = '"+dszh+"' ");
        }else{
            seach.append(" and dszh = '" + dszh + "' ");
            seach.append(" and qunzhxx is null ");
        }
        seach.append(" order by fstime");
        // 从session域中取出数据
        Page page = wfcService.getFriendChat(pageNo,pageSize,seach.toString());
        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(gson.toJson(page));
        return gson.toJson(page);
    }

    @RequestMapping(value = "/getDetailsByFilter",method = RequestMethod.POST)
    public @ResponseBody String getDetailsByFilter (String zhxx,String dszh,String term,HttpSession session){
        Long aj_id = (Long) session.getAttribute("aj_id");
        StringBuffer seach = new StringBuffer();
        seach.append(" and aj_id = "+aj_id);
        seach.append( " and zhxx = '" + zhxx + "'");
        if(dszh.contains("@")){
            seach.append(" and qunzhxx = '"+dszh+"'");
        }else{
            seach.append(" and dszh = '" + dszh + "'");
            seach.append(" and qunzhxx is null");
        }
        seach.append(" order by fstime ");

        return wfcService.getChatByFilter(seach.toString(),term.trim());
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
    /*@RequestMapping(value = "/getDetails", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDetails(String fswechatno, String jswechatno, int page, String order, HttpSession session) {
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoWechatLtjlEntity.class);
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
    }*/

}