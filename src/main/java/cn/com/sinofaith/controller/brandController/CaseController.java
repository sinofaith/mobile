package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.CaseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/case")
public class CaseController {
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
        BrandEntity be = (BrandEntity) req.getSession().getAttribute("brand");
        RegionEntity re = (RegionEntity) req.getSession().getAttribute("region");
        String seachCondition = (String) req.getSession().getAttribute("bseachCondition");
        String seachCode = (String) req.getSession().getAttribute("bseachCode");
        String seach = cs.getSeach(seachCode,seachCondition,re !=null?re:new RegionEntity());
        Page page = cs.queryForPage(parseInt(pageNo),10,seach);
        mav.addObject("page",page);
        mav.addObject("bseachCode",seachCode);
        mav.addObject("bseachCondition",seachCondition);
        mav.addObject("detailinfo",page.getList());
        mav.addObject("brand",be);
        mav.addObject("region",re);
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
    public String getCase(@RequestParam("caseName") String caseName,@RequestParam("regionId")String regionId){
        CaseEntity ce = cs.getCase(caseName,Long.parseLong(regionId));
        if(ce.getCaseId()!=-1){
            return "303";
        }
        return "200";
    }

    @RequestMapping(value = "/case")
    public ModelAndView jump(@RequestParam("caseId") String regionId, HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/phone/seach?pageNo=1");


        httpSession.setAttribute("aj",cs.getById(Long.parseLong(regionId)));
        return mav;
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("caseName")String caseName,
                      @RequestParam("creater") String creater,@RequestParam("regionId")String regionId){

        CaseEntity ce = cs.getCase(caseName,Long.parseLong(regionId));
        if(ce.getCaseId()!=-1){
            return "303";
        }else {
            cs.add(caseName,creater,Long.parseLong(regionId));
            return "200";
        }
    }


    @RequestMapping(value = "/getCaseName",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRoleName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(cs.getDopage(term,"case_name"));
    }

    @RequestMapping(value = "/getCreater",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRegionName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(cs.getDopage(term,"creater"));
    }
}
