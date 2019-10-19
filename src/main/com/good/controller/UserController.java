package com.good.controller;

import com.good.model.UserVO;
import com.good.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public String getUserList(Model model) throws Exception {
        model.addAttribute("userList",userService.getUserList());
        return "user/userList";
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("userVO") UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
        userService.insertUser(userVO);
        return "redirect:/user/getUserList";
    }
}
