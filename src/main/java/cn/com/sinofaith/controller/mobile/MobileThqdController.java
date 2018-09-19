package cn.com.sinofaith.controller.mobile;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoThqdEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.mobile.ThqdService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *  手机通话清单信息控制器
 */
@Controller
@RequestMapping("/phoneThqd")
public class MobileThqdController {

    @Autowired
    private ThqdService thqdService;

    /**
     * 跳转请求
     * @param flag
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView mobileThqd(String flag, HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/phoneThqd/seach?pageNo=1");
        // 将session域中数据清空
        session.removeAttribute("phoneThqdSeachCode"); //查询内容
        session.removeAttribute("phoneThqdSeachCondition");//查询条件
        session.removeAttribute("phoneThqdLastOrder");
        session.removeAttribute("phoneThqdDesc");
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
    public String seach(int pageNo, String orderby, HttpSession session, Model model){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoThqdEntity.class);
        // 将域中对象取出
        String seachCode = (String) session.getAttribute("phoneThqdSeachCode");
        String seachCondition = (String) session.getAttribute("phoneThqdSeachCondition");
        String lastOrder = (String) session.getAttribute("phoneThqdLastOrder");
        String desc = (String) session.getAttribute("phoneThqdDesc");
        CaseEntity aj = (CaseEntity) session.getAttribute("aj");
        if(aj==null){
            return "mobile/phoneThqd";
        }
        dc.add(Restrictions.eq("aj_id",aj.getCaseId()));
        if(seachCode!=null && !seachCode.trim().equals("")){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        if(orderby!=null){
            if(orderby.equals(lastOrder)){
                if(desc==null || desc.equals("desc")){
                    dc.addOrder(Order.desc(orderby));
                    desc = "";
                }else{
                    dc.addOrder(Order.asc(orderby));
                    desc = "desc";
                }
            }else{
                dc.addOrder(Order.desc(orderby));
                desc = "";
            }
        }
        // 调用service获取分页对象
        Page page = thqdService.queryForPage(pageNo,4,dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        session.setAttribute("phoneThqdLastOrder",orderby);
        session.setAttribute("phoneThqdDesc",desc);
        model.addAttribute("phone","phoneThqd");
        return "mobile/phoneThqd";
    }


    /**
     * 设置查询条件
     * @param seachCondition
     * @param seachCode
     * @param session
     * @return
     */
    @RequestMapping("/seachCode")
    public String seachCode(String seachCondition, String seachCode, HttpSession session){
        // 判断seachCode是否为null或为空
        if(seachCode==null && seachCode.isEmpty()){
            session.removeAttribute("phoneThqdSeachCode");
            session.removeAttribute("phoneThqdSeachCondition");
            return "redirect:/phoneThqd/seach?pageNo=1";
        }
        session.setAttribute("phoneThqdSeachCode",seachCode);
        session.setAttribute("phoneThqdSeachCondition",seachCondition);
        return "redirect:/phoneThqd/seach?pageNo=1";
    }

}
