package cn.com.sinofaith.controller.qqController;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoQqZhxxEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.phone.ZhxxService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Distinct;
import org.hibernate.criterion.Projections;
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
 * 手机qq账户信息控制器
 */
@Controller
@RequestMapping("/phoneZhxx")
public class ZhxxController {

    @Autowired
    private ZhxxService zhxxService;

    @RequestMapping()
    public ModelAndView phoneZhxx(String flag, HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/phoneZhxx/seach?pageNo=1");
        session.removeAttribute("zhxxSeachCode"); //查询内容
        session.removeAttribute("zhxxSeachCondition");//查询条件
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
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoQqZhxxEntity.class);
        // 取出session域中的数据
        String seachCode = (String) session.getAttribute("zhxxSeachCode");
        String seachCondition = (String) session.getAttribute("zhxxSeachCondition");
        Long aj_id = (Long) session.getAttribute("aj_id");
        if(aj_id==null || aj_id==0){
            return "phone/phoneinfo";
        }
        dc.add(Restrictions.eq("aj_id",aj_id));
        // 判断此时域中是否有seachCode
        if(seachCode!=null && !seachCode.isEmpty()){
            dc.add(Restrictions.like(seachCondition,"%"+seachCode+"%"));
        }
        // 调用Service获得分页数据
        Page page = zhxxService.queryForPage(parseInt(pageNo), 10, dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        model.addAttribute("phone","phoneZhxx");
        return "phone/phoneinfo";
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
            session.removeAttribute("zhxxSeachCode");
            session.removeAttribute("zhxxSeachCondition");
            return "redirect:/phoneZhxx/seach?pageNo=1";
        }
        session.setAttribute("zhxxSeachCode",seachCode);
        session.setAttribute("zhxxSeachCondition",seachCondition);
        return "redirect:/phoneZhxx/seach?pageNo=1";
    }

}
