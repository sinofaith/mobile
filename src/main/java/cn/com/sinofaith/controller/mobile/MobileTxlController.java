package cn.com.sinofaith.controller.mobile;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoTxlEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.mobile.TxlService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *  手机通讯录信息控制器
 */
@Controller
@RequestMapping("/phoneTxl")
public class MobileTxlController {

    @Autowired
    private TxlService txlService;

    /**
     * 跳转请求
     * @param flag
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView mobileTxl(String flag, HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/phoneTxl/seach?pageNo=1");
        // 将session域中数据清空
        session.removeAttribute("phoneTxlSeachCode"); //查询内容
        session.removeAttribute("phoneTxlSeachCondition");//查询条件
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
    public String seach(int pageNo, HttpSession session, Model model){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoTxlEntity.class);
        // 将域中对象取出
        String seachCode = (String) session.getAttribute("phoneTxlSeachCode");
        String seachCondition = (String) session.getAttribute("phoneTxlSeachCondition");
        CaseEntity aj = (CaseEntity) session.getAttribute("aj");
        if(aj==null){
            return "mobile/phoneTxl";
        }
        dc.add(Restrictions.eq("aj_id",aj.getCaseId()));
        if(seachCode!=null && !seachCode.trim().equals("")){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        // 调用service获取分页对象
        Page page = txlService.queryForPage(pageNo,10,dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        model.addAttribute("phone","phone");
        return "mobile/phoneTxl";
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
            session.removeAttribute("phoneTxlSeachCode");
            session.removeAttribute("phoneTxlSeachCondition");
            return "redirect:/phoneTxl/seach?pageNo=1";
        }
        session.setAttribute("phoneTxlSeachCode",seachCode);
        session.setAttribute("phoneTxlSeachCondition",seachCondition);
        return "redirect:/phoneTxl/seach?pageNo=1";
    }


}
