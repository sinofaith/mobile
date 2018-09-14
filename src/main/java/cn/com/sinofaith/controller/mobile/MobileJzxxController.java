package cn.com.sinofaith.controller.mobile;

import cn.com.sinofaith.bean.AjEntity;
import cn.com.sinofaith.bean.TAutoJzxxEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.mobile.JzxxService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *  手机机主信息控制器
 */
@Controller
@RequestMapping("/phone")
public class MobileJzxxController {

    @Autowired
    private JzxxService jzxxService;

    /**
     * 跳转请求
     * @param flag
     * @param session
     * @return
     */
    @RequestMapping()
    public ModelAndView mobileJzxx(String flag, HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/phone/seach?pageNo=1");
        // 将session域中数据清空
        session.removeAttribute("phoneJzxxSeachCode"); //查询内容
        session.removeAttribute("phoneJzxxSeachCondition");//查询条件
        if(flag==null){
            flag = "a11";
        }
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
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoJzxxEntity.class);
        // 将域中对象取出
        String seachCode = (String) session.getAttribute("phoneJzxxSeachCode");
        String seachCondition = (String) session.getAttribute("phoneJzxxSeachCondition");
        AjEntity aj = (AjEntity) session.getAttribute("aj");

        //-----------测试环境---------
        if(aj==null){
            aj =new AjEntity();
            aj.setId(1);
        }
        //---------------------------
        dc.add(Restrictions.eq("aj_id",aj.getId()));
        if(seachCode!=null && !seachCode.trim().equals("")){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        // 调用service获取分页对象
        Page page = jzxxService.queryForPage(pageNo,4,dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        model.addAttribute("phone","phone");
        return "mobile/phoneJzxxinfo";
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
            session.removeAttribute("phoneJzxxSeachCode");
            session.removeAttribute("phoneJzxxSeachCondition");
            return "redirect:/phone/seach?pageNo=1";
        }
        session.setAttribute("phoneJzxxSeachCode",seachCode);
        session.setAttribute("phoneJzxxSeachCondition",seachCondition);
        return "redirect:/phone/seach?pageNo=1";
    }


}
