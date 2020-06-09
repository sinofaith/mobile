package cn.com.sinofaith.controller;

import cn.com.sinofaith.bean.UserEntity;
import cn.com.sinofaith.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;

@Controller
public class LoginController {
    @Autowired
    private UserServices us;

    @RequestMapping(value = "/homepage")
    public String redirectHome() {

        return "newlogin";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "newlogin";
    }

    @RequestMapping(value = "/login")
    public ModelAndView validateLogin(@ModelAttribute("userForm") UserEntity userEntity, HttpSession session) {

        ModelAndView mav = new ModelAndView();
        UserEntity user = us.login(userEntity);
        if(user!=null){
            mav = new ModelAndView("redirect:/caseBrand");
            session.setAttribute("user",user);
        }else {
            mav = new ModelAndView("newlogin");
            mav.addObject("username",userEntity.getUsername());
            mav.addObject("result", "用户名或密码错误");
        }
        session.setAttribute("sFlg",1);
        return mav;
    }
}