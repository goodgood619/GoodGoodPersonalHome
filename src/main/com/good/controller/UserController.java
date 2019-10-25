package com.good.controller;

import com.good.model.UserVO;
import com.good.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public String getUserList(Model model) throws Exception {
        model.addAttribute("userList",userService.getUserList());
        return "user/userList";
    }

    @ResponseBody
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Map<String,Object> insertUser(@RequestBody UserVO userVO) throws Exception {
        Map<String,Object> result = new HashMap<>();
        UserVO userVO1 = userService.getIdcheck(userVO.getId());
        if(userVO1 != null){
            result.put("good","아이디중복");
        }
        else {
            String s = cellPhonenumCheck(userVO.getCellphone());
            if(s.equals("no")){
                result.put("good","핸드폰문자");
            }
            else {
                String s2 = cellPhonelengthCheck(userVO.getCellphone());
                if(s2.equals("no")){
                    result.put("good","핸드폰길이불가");
                }
                else {
                    if(emailCheck(userVO.getEmail()).equals("no")){
                        result.put("good","이메일골뱅이");
                    }
                    else {
                        try{
                            userService.insertUser(userVO);
                            result.put("good","회원가입성공");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping(value="/sendIdcheck",method = RequestMethod.POST)
    public String sendIdcheck(@ModelAttribute("userVO") UserVO userVO) throws Exception {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

            messageHelper.setFrom(new InternetAddress("gktgnjftm@naver.com"));
            messageHelper.setTo(new InternetAddress(userVO.getEmail(),"회원님"));
            messageHelper.setSubject("뚱석");
            messageHelper.setText("뚱석이에용 둥둥");
            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "user/findIdInfo";
    }


    public String cellPhonenumCheck(String cellphone) throws Exception {
        for(int i=0;i<cellphone.length();i++) {
            if(cellphone.charAt(i)=='-' || cellphone.charAt(i)>='0' && cellphone.charAt(i)<='9') continue;
            else {
                return "no";
            }
        }
        return "yes";
    }
    public String cellPhonelengthCheck(String cellphone) throws Exception {
        List<Character> list = new ArrayList<>();
        for(int i=0; i< cellphone.length();i++) {
            if(cellphone.charAt(i)=='-') continue;
            list.add(cellphone.charAt(i));
        }
        if(list.size()<10 || list.size()>11) return "no";
        else return list.toString();
    }
    public String emailCheck(String email) throws Exception{
        for(int i=0 ; i<email.length(); i++) {
            if(email.charAt(i)=='@') return "yes";
        }
        return "no";
    }
    @ResponseBody
    @RequestMapping(value = "/idCheck",method = RequestMethod.POST)
    public Map<String,Object> idCheck(@RequestBody UserVO userVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        UserVO userVO1 = userService.getIdcheck(userVO.getId());
        if(userVO1 == null){
            result.put("status","실패");
        }
        else if(userVO1.getId() != null){
            result.put("status","성공");
        }
        return result;
    }


}
