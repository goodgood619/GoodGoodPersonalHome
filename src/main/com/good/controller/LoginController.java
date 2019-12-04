package com.good.controller;

import com.good.model.UserVO;
import com.good.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/signupForm",method = RequestMethod.GET)
    public String signupForm(Model model) throws Exception{
        model.addAttribute("userVO",new UserVO());
        return "login/signupForm";
    }

    @RequestMapping(value = "/choosefindId_Email_Phone", method = RequestMethod.GET)
    public String choosefindId_Email_Phone() throws Exception{
        return "login/choosefindId_Email_Phone";
    }

    @RequestMapping(value = "/findIdInfo",method = RequestMethod.GET)
    public String findIdInfo(Model model) throws Exception {
        model.addAttribute("userVO",new UserVO());
        return "login/findIdInfo";
    }


    @RequestMapping(value= "/findIdInfo_phone",method = RequestMethod.GET)
    public String findIdInfo_phone(Model model) throws Exception {
        model.addAttribute("userVO",new UserVO());
        return "login/findIdInfo_phone";
    }

    @RequestMapping(value = "/findPwdInfo",method = RequestMethod.GET)
    public String findPwdInfo(Model model) throws Exception{
        model.addAttribute("userVO",new UserVO());
        return "login/findPwdInfo";
    }

    @RequestMapping(value = "/doinitLogin",method = RequestMethod.GET)
    public String doinitLogin(@ModelAttribute("userVO") UserVO userVO) throws Exception {
        return "login/login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("userVO") UserVO userVO, Model model, HttpServletRequest req, RedirectAttributes redirectAttributes) throws Exception {
        HttpSession httpSession = req.getSession();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",userVO.getId());
        hashMap.put("pwd",userVO.getPwd());
        UserVO resultuserVO = userService.getLoginInfo(hashMap);

        if(SessionListener.getInstance().isUsing(userVO.getId())){
            redirectAttributes.addFlashAttribute("msg","false2");
            return "redirect:/login/doinitLogin";
        }
        if(resultuserVO != null){
            httpSession.setAttribute("member",resultuserVO);
            SessionListener.getInstance().valueBound(httpSession);
            return "redirect:/board/getBoardList";
        }
        else {
            redirectAttributes.addFlashAttribute("msg","false");
            return "redirect:/login/doinitLogin";
        }
    }

    @RequestMapping(value = "/doLogout",method = RequestMethod.GET)
    public String doLogout(HttpSession httpSession) {
        SessionListener.getInstance().valueUnbound(httpSession);
        httpSession.invalidate();
        return "redirect:/login/doinitLogin";
    }
}
