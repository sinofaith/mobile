package cn.com.sinofaith.controller.mobile;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.TAutoJzxxEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.mobile.JzxxService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
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
    public ModelAndView mobileJzxx(String flag, @RequestParam(value="aj_id", defaultValue="0") long aj_id,
                                   HttpSession session){
        ModelAndView mav = new ModelAndView("redirect:/phone/seach?pageNo=1");
        // 将session域中数据清空
        session.removeAttribute("phoneJzxxSeachCode"); //查询内容
        session.removeAttribute("phoneJzxxSeachCondition");//查询条件
        Long aj_id1 = (Long) session.getAttribute("aj_id");
        if(aj_id1==null || aj_id1==0 || aj_id!=0){
            session.setAttribute("aj_id",aj_id);
        }

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
        long aj_id = (long) session.getAttribute("aj_id");
        if(aj_id==0){
            return "mobile/phoneJzxxinfo";
        }
        dc.add(Restrictions.eq("aj_id",aj_id));
        if(seachCode!=null && !seachCode.trim().equals("")){
            dc.add(Restrictions.like(seachCondition,"%"+seachCode+"%"));
        }
        // 调用service获取分页对象
        Page page = jzxxService.queryForPage(pageNo,10,dc);
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

    /**
     * 获取要修改的机主信息
     * @param id
     * @return
     */
    @RequestMapping("/getEditPerson")
    public @ResponseBody TAutoJzxxEntity getEditPerson(long id){
        TAutoJzxxEntity jzxxEntity = jzxxService.getEditPerson(id);
        return jzxxEntity;
    }

    /**
     * 修改机主信息
     * @param jzxxEntity
     * @return
     */
    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    @ResponseBody
    public String editPerson(@RequestBody TAutoJzxxEntity jzxxEntity){
        jzxxService.editPerson(jzxxEntity);
        return "200";
    }
}
