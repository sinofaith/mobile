package cn.com.sinofaith.controller;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.BrandService;
import cn.com.sinofaith.service.brand.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/case")
public class CaseController {
    @Autowired
    private BrandService bs;
    @Autowired
    private CaseService cs;
    @RequestMapping()
    public ModelAndView home(HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/case/seach?pageNo=1");
        httpSession.removeAttribute("bseachCode");
        httpSession.removeAttribute("bseachCondition");

        return mav;
    }

    @RequestMapping(value = "/seach")
    public ModelAndView getCase(@RequestParam("pageNo") String pageNo, HttpServletRequest req){
        ModelAndView mav = new ModelAndView("case/case");

        String seachCondition = (String) req.getSession().getAttribute("bseachCondition");
        String seachCode = (String) req.getSession().getAttribute("bseachCode");
        String seach = cs.getSeach(seachCode,seachCondition);
        Page page = cs.queryForPage(parseInt(pageNo),10,seach);
        mav.addObject("page",page);
        mav.addObject("bseachCode",seachCode);
        mav.addObject("bseachCondition",seachCondition);
        mav.addObject("detailinfo",page.getList());
        return mav;
    }

    @RequestMapping(value = "/SeachCode" , method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView seachCode(String seachCode,String seachCondition,HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/case/seach?pageNo=1");
        if(seachCode == null || seachCode.isEmpty()){
            httpSession.removeAttribute("bseachCode");
            httpSession.removeAttribute("bseachCondition");
            return mav;
        }

        httpSession.setAttribute("bseachCondition",seachCondition);
        httpSession.setAttribute("bseachCode",seachCode);
        return mav;
    }
    @RequestMapping(value = "/getCase",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getCase(@RequestParam("casename") String casename){
        List<CaseEntity> list = cs.getCaseByName(casename);
        if(list.size()>0){
            return "303";
        }
        return "200";
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("brandname") String brandname){
        if(bs.getByname(brandname)==null){
            if(bs.add(brandname)>0) {
                return "200";
            }
            return "404";
        }
        return "303";
    }
}
