package com.good.controller;

import com.good.model.UserVO;
import com.good.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.HashMap;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/signupForm",method = RequestMethod.GET)
    public String signupForm(Model model) throws Exception{
        model.addAttribute("userVO",new UserVO());
        return "user/signupForm";
    }

    @RequestMapping(value = "/doinitLogin",method = RequestMethod.GET)
    public String doinitLogin(@ModelAttribute("userVO") UserVO userVO) throws Exception {
        return "login/login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("userVO") UserVO userVO,Model model) throws Exception {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",userVO.getId());
        hashMap.put("pwd",userVO.getPwd());
        UserVO resultuserVO = userService.getLoginInfo(hashMap);
        if(resultuserVO != null){
            return "redirect:/user/getUserList";
        }
        else {
            model.addAttribute("status","false");
            return "login/login";
        }
    }
}
