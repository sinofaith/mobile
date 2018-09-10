package cn.com.sinofaith.controller.wxController;

import cn.com.sinofaith.bean.AjEntity;
import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.wxPhone.WxZhxxService;
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
 * 手机微信账户信息控制器
 */
@Controller
@RequestMapping("/wxPhone")
public class WxZhxxController {

    @Autowired
    private WxZhxxService zhxxService;

    @RequestMapping()
    public ModelAndView phoneZhxx(HttpSession session, String flag){
        ModelAndView mav = new ModelAndView("redirect:/wxPhone/seach?pageNo=1");
        session.removeAttribute("wxZhxxSeachCode"); //查询条件
        session.removeAttribute("wxZhxxSeachCondition");//查询内容
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
        DetachedCriteria dc = DetachedCriteria.forClass(TAutoWechatZhxxEntity.class);
        // 取出session域中的数据
        String seachCode = (String) session.getAttribute("wxZhxxSeachCode");
        String seachCondition = (String) session.getAttribute("wxZhxxSeachCondition");
        AjEntity aj = (AjEntity) session.getAttribute("aj");
        if(aj==null){
            aj =new AjEntity();
            aj.setId(1);
        }
        dc.add(Restrictions.eq("aj_id",aj.getId()));
        // 判断此时域中是否有seachCode
        if(seachCode!=null && !seachCode.isEmpty()){
            dc.add(Restrictions.like(seachCondition,seachCode));
        }
        // 调用Service获得分页数据
        Page page = zhxxService.queryForPage(parseInt(pageNo), 4, dc);
        if(page!=null){
            model.addAttribute("page",page);
            model.addAttribute("detailinfo",page.getList());
        }
        model.addAttribute("phone","wxPhone");
        return "phone/phoneWXinfo";
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
            session.removeAttribute("wxZhxxSeachCode");
            session.removeAttribute("wxZhxxSeachCondition");
            return "redirect:/wxPhone/seach?pageNo=1";
        }
        session.setAttribute("wxZhxxSeachCode",seachCode);
        session.setAttribute("wxZhxxSeachCondition",seachCondition);
        return "redirect:/wxPhone/seach?pageNo=1";
    }

}
