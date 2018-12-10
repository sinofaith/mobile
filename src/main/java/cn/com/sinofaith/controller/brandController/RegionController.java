package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.bean.RoleEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.RegionService;
import cn.com.sinofaith.util.TimeFormatUtil;
import com.google.gson.Gson;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            dc.add(Restrictions.like(seachCondition,"%"+seachCode+"%"));
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

    /**
     * 获取已有人员名
     * @param term
     * @return
     */
    @RequestMapping("/getRoleName")
    public @ResponseBody Set<String> getRoleName(String term){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(RoleEntity.class);
        // 封装条件
        if(!term.equals("")){
            dc.add(Restrictions.like("role_name","%"+term+"%"));
        }
        return rs.getDo(1, 100, dc, "role_name");
    }

    /**
     * 获取已有角色名
     * @param term
     * @return
     */
    @RequestMapping("/getRole")
    public @ResponseBody Set<String> getRole(String term){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(RoleEntity.class);
        // 封装条件
        if(!term.equals("")){
            dc.add(Restrictions.like("role","%"+term+"%"));
        }
        return rs.getDo(1, 100, dc, "role");
    }


    @RequestMapping( value = "/getPerson",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getPerson(String role_id){
        Gson gson = new Gson();
        Map map = new HashMap();
        return gson.toJson(rs.getRoleById(Long.parseLong(role_id)));
    }

    @RequestMapping(value = "/editRole",method = RequestMethod.POST,produces = "text/pain;charset=UTF-8")
    @ResponseBody
    public String editRole(String role_name,String sfzhm,String role,String role_id,String inserttime,String region_id){
        RoleEntity re = new RoleEntity();
        re.setRole_name(role_name);
        re.setSfzhm(sfzhm);
        re.setRole(role);
        re.setRole_id(Long.parseLong(role_id));
        re.setInsertTime(inserttime);
        re.setRegion_id(Long.parseLong(region_id));
        rs.editRole(re);
        return "200";
    }

    /**
     * 获取已有身份证
     * @param sfzhm
     * @param session
     * @return
     */

    @RequestMapping("/getSFZHM")
    @ResponseBody
    public String getSFZHM(String sfzhm,HttpSession session){
        // 获取区域id
        Integer regionId = (Integer) session.getAttribute("regionId");
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(RoleEntity.class);
        // 添加查询条件
        dc.add(Restrictions.eq("sfzhm",sfzhm));
        long id = Long.parseLong(regionId.toString());
        dc.add(Restrictions.eq("region_id",id));
        // 获取条数
        int rowAll = rs.getRoleRowBySFZHM(dc);
        // 判断该区域是否已有这个身份证号码
        if(rowAll>0){
            return "303";
        }
        return "200";
    }

    /**
     * 添加人员
     * @param role_name
     * @param sfzhm
     * @param role
     * @param session
     * @return
     */
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public String addRole(String role_name,String sfzhm,String role, HttpSession session){
        // 获取区域id
        Integer regionId = (Integer) session.getAttribute("regionId");
        long id = Long.parseLong(regionId.toString());
        // 设置一个添加人员对象
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole_name(role_name);
        roleEntity.setRegion_id(id);
        roleEntity.setSfzhm(sfzhm);
        roleEntity.setRole(role);
        roleEntity.setInsertTime(TimeFormatUtil.getDate("/"));
        long i = rs.addRole(roleEntity);
        // 删除gainList
//        Jedis jedis = JedisPoolUtils.getJedis();
//        jedis.del("annualDataList");
//        JedisPoolUtils.returnResource(jedis);
        if(i>0){
            return "200";
        }else{
            return "303";
        }
    }
}
