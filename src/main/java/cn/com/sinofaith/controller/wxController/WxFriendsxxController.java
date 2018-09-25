package cn.com.sinofaith.controller.wxController;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoWechatFriendsxxEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.wxPhone.WxFriendxxService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

/**
 * 手机微信好友群信息控制器
 */
@Controller
@RequestMapping("/phonewxFriends")
public class WxFriendsxxController {

    @Autowired
    private WxFriendxxService friendxxService;

    @RequestMapping()
    public ModelAndView phonefriendsxx(HttpSession session, String flag){
        ModelAndView mav = new ModelAndView("redirect:/phonewxFriends/seach?pageNo=1");
        session.removeAttribute("wxFriendsxxSeachCode"); //查询条件
        session.removeAttribute("wxFriendsxxSeachCondition");//查询内容
//        session.removeAttribute("wuliuRelationOrder");
//        session.removeAttribute("wuliuRelationlastOrder");
//        session.removeAttribute("wuliuRelationDesc");
        session.setAttribute("flag", flag);
        return mav;
    }

    /**
     * 分页数据
     * @param pageNo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/seach")
    public String seach(String pageNo, HttpSession session, Model model){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoWechatFriendsxxEntity.class);
        // 取出session域中的数据
        String seachCode = (String) session.getAttribute("wxFriendsxxSeachCode");
        String seachCondition = (String) session.getAttribute("wxFriendsxxSeachCondition");
        CaseEntity aj = (CaseEntity) session.getAttribute("aj");
        if(aj==null){
            return "phone/phoneWXfriends";
        }
        dc.add(Restrictions.eq("aj_id",aj.getCaseId()));
        dc.add(Restrictions.isNotNull("friendqh"));
        // 判断此时域中是否有seachCode
        if(seachCode!=null && !seachCode.isEmpty()){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        // 调用Service获得分页数据
        Page page = friendxxService.queryForPage(parseInt(pageNo), 10, dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        model.addAttribute("phone","phonewxFriends");
        return "phone/phoneWXfriends";
    }

    /**
     * 根据字段名的seachCode进行(模糊)查询
     * @param seachCondition
     * @param seachCode
     * @param session
     * @return
     */
    @RequestMapping(value = "/seachCode", method = RequestMethod.POST)
    public String  seachCode(String seachCode, String seachCondition, HttpSession session){
        // 判断seachCode是否为null或为空
        if(seachCode==null && seachCode.isEmpty()){
            session.removeAttribute("wxFriendsxxSeachCode");
            session.removeAttribute("wxFriendsxxSeachCondition");
            return "redirect:/phonewxFriends/seach?pageNo=1";
        }
        session.setAttribute("wxFriendsxxSeachCode",seachCode);
        session.setAttribute("wxFriendsxxSeachCondition",seachCondition);
        return "redirect:/phonewxFriends/seach?pageNo=1";
    }

}
