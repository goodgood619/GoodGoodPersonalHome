package com.good.controller;

import com.good.model.UserVO;
import com.good.controller.LoginController;
import com.good.service.UserService;
import com.google.protobuf.RpcUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Inject
    UserService userService;

    @Inject
    UserController userController;

    @RequestMapping(value = "/showMemberuser",method = RequestMethod.GET)
    public String showMemberuser(Model model,HttpSession httpSession) throws Exception {
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        HashMap<String,Object> ret = new HashMap<>();
        ret.put("id",userVO.getId());
        ret.put("pwd",userVO.getPwd());
        UserVO userVO1 = userService.getMemberInfo(ret);
        model.addAttribute("UserVO",userVO1);
        model.addAttribute("userVO",new UserVO());
        return "member/memberStatus";
    }

    @ResponseBody
    @RequestMapping(value= "/modifyMemberuser",method = RequestMethod.POST)
    public Map<String,Object> modifyMemberuser(@RequestBody UserVO userVO,HttpSession httpSession) throws Exception {
        //수정
        UserVO origin = (UserVO) httpSession.getAttribute("member");
        HashMap<String,Object> send = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        String s = userController.cellPhonenumCheck(userVO.getCellphone());
        if ( s.equals("no") ) {
            result.put("good", "핸드폰문자");
        }
        else {
            String s2 = userController.cellPhonelengthCheck(userVO.getCellphone());
            if ( s2.equals("no")) {
                result.put("good", "핸드폰길이불가");
            }
            else {
                if ( userController.emailCheck(userVO.getEmail()).equals("no") ) {
                    result.put("good", "이메일골뱅이");
                }
                else {
                    try {
                        userVO.setCellphone(s2);
                        send.put("id",userVO.getId());
                        send.put("pwd",userVO.getPwd());
                        send.put("name",userVO.getName());
                        send.put("cellphone",userVO.getCellphone());
                        send.put("email",userVO.getEmail());
                        send.put("oid",origin.getId());
                        userService.updateMember(send);
                        result.put("good", "회원정보수정");
                        httpSession.removeAttribute("member");
                        httpSession.setAttribute("member",userVO);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
