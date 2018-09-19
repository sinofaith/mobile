package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.RegionService;
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
@RequestMapping("/caseRegion")
public class RegionController {
    @Autowired
    private RegionService rs;

    @RequestMapping()
    public ModelAndView home(HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/caseRegion/seach?pageNo=1");
        httpSession.removeAttribute("rbseachCode");
        httpSession.removeAttribute("rbseachCondition");

        return mav;
    }

    @RequestMapping(value = "/seach")
    public ModelAndView getCase(@RequestParam("pageNo") String pageNo, HttpServletRequest req){
        ModelAndView mav = new ModelAndView("case/caseRegion");
        BrandEntity be = (BrandEntity)req.getSession().getAttribute("brand");
        String seachCondition = (String) req.getSession().getAttribute("rbseachCondition");
        String seachCode = (String) req.getSession().getAttribute("rbseachCode");
        String seach = rs.getSeach(seachCode,seachCondition,be!=null?be:new BrandEntity());
        Page page = rs.queryForPage(parseInt(pageNo),10,seach);
        mav.addObject("page",page);
        mav.addObject("rbseachCode",seachCode);
        mav.addObject("rbseachCondition",seachCondition);
        mav.addObject("detailinfo",page.getList());
        mav.addObject("brand",be);
        return mav;
    }
    @RequestMapping(value = "/SeachCode" , method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView seachCode(String seachCode,String seachCondition,HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/caseRegion/seach?pageNo=1");
        if(seachCode == null || seachCode.isEmpty()){
            httpSession.removeAttribute("rbseachCode");
            httpSession.removeAttribute("rbseachCondition");
            return mav;
        }

        httpSession.setAttribute("rbseachCondition",seachCondition);
        httpSession.setAttribute("rbseachCode",seachCode);
        return mav;
    }

//    @RequestMapping(value = "/getBrandName" ,method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    public String getBrandName(@RequestParam("term")String term){
//        Gson gson = new Gson();
//        return gson.toJson(rs.getDopage(term,"brand_name"));
//    }
//
//    @RequestMapping(value = "/getUnitName" ,method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    public String getUnitName(@RequestParam("term")String term){
//        Gson gson = new Gson();
//        return gson.toJson(rs.getDopage(term,"unit_name"));
//    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("unitId")String unitId, @RequestParam("regionName") String regionName,
                                    @RequestParam("roleName")String roleName){
        RegionEntity re = rs.getRegion(Long.parseLong(unitId),regionName);
        if(re.getRegionId()!=-1){
            return "303";
        }else {
            rs.add(Long.parseLong(unitId),regionName,roleName);
            return "200";
        }
    }
    @RequestMapping(value = "/getRegion",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRegion(@RequestParam("unitId") String unitId,@RequestParam("regionName") String regionName){
        RegionEntity re = rs.getRegion(Long.parseLong(unitId),regionName);
        if(re.getRegionId()!=-1){
            return "303";
        }else {
            return "200";
        }
    }
    @RequestMapping(value = "/region")
    public ModelAndView jump(@RequestParam("regionId") String regionId, HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/case/seach?pageNo=1");

        httpSession.removeAttribute("bseachCode");
        httpSession.removeAttribute("bseachCondition");
        httpSession.setAttribute("region",rs.getById(Long.parseLong(regionId)));
        return mav;
    }

    @RequestMapping(value = "/getRoleName",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRoleName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(rs.getDopage(term,"role_name"));
    }

    @RequestMapping(value = "/getRegionName",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRegionName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(rs.getDopage(term,"region_name"));
    }
}
