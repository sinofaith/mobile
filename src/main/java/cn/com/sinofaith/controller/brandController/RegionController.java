package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.bean.RoleEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.RegionService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Retention;

@Controller
@RequestMapping("/caseRegion")
public class RegionController {
    @Autowired
    private RegionService rs;

    @RequestMapping()
    public ModelAndView caseRegion(HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/caseRegion/seach?pageNo=1");
        // 清空数据
        session.removeAttribute("rbSeachCode");
        session.removeAttribute("rbSeachCondition");
        return mav;
    }

    /**
     * 分页查询
     * @param pageNo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/seach")
    public String seach(int pageNo, HttpSession session, Model model){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(RoleEntity.class);
        // 获取session数据
        Integer regionId = (Integer) session.getAttribute("regionId");
        if(regionId!=null){
            long id = Long.parseLong(regionId.toString());
            dc.add(Restrictions.eq("region_id",id));

        }else{
            return "case/caseRegion";
        }
        // 条件内容
        String seachCode = (String) session.getAttribute("rbSeachCode");
        // 条件字段
        String seachCondition = (String) session.getAttribute("rbSeachCondition");
        if(seachCode!=null && !seachCode.trim().equals("")){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        // 获取分页数据
        Page page = rs.getDoPage(pageNo,10,dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        return "case/caseRegion";
    }

    /**
     * 查询条件
     * @param seachCondition
     * @param seachCode
     * @param session
     * @return
     */
    @RequestMapping("/SeachCode")
    public String seachCode(String seachCondition, String seachCode, HttpSession session){
        // 判断seachCode是否为null或为空
        if(seachCode==null && seachCode.isEmpty()){
            session.removeAttribute("rbSeachCode");
            session.removeAttribute("rbSeachCondition");
            return "redirect:/caseRegion/seach?pageNo=1";
        }
        session.setAttribute("rbSeachCode",seachCode);
        session.setAttribute("rbSeachCondition",seachCondition);
        return "redirect:/caseRegion/seach?pageNo=1";
    }

    @RequestMapping("/getRoleName")
    @ResponseBody
    public String getRoleName(String term){
        return null;
    }
}
