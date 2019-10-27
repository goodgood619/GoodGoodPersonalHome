package com.good.controller;

import com.good.model.UserVO;
import com.good.service.UserService;
import net.nurigo.java_sdk.Coolsms;
import net.sf.json.JSON;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.mail.Message;
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
        String idcheck = userService.getIdcheck(userVO.getId());
        if(idcheck != null) {
            result.put("good","아이디중복");
        }
        else {
            String emailcheck = userService.getemailCheck(userVO.getEmail());
            if ( emailcheck != null ) {
                result.put("good","이메일중복");
            }
            else {
                String s = cellPhonenumCheck(userVO.getCellphone());
                if ( s.equals("no") ) {
                    result.put("good", "핸드폰문자");
                } else {
                    String s2 = cellPhonelengthCheck(userVO.getCellphone());
                    if ( s2.equals("no") ) {
                        result.put("good", "핸드폰길이불가");
                    } else {
                        if ( emailCheck(userVO.getEmail()).equals("no") ) {
                            result.put("good", "이메일골뱅이");
                        } else {
                            try {
                                userService.insertUser(userVO);
                                result.put("good", "회원가입성공");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping(value="/sendIdcheck",method = RequestMethod.POST)
    public String sendIdcheck(@ModelAttribute("userVO") UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        result.put("email",userVO.getEmail());
        String id = userService.sendIdcheck(result);

        if(id != null) {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(new InternetAddress("gktgnjftm@naver.com","운영자"));
                messageHelper.setTo(new InternetAddress(userVO.getEmail(), "회원님"));
                messageHelper.setSubject("뚱석 홈페이지 아이디 찾기 관련입니다.");
                messageHelper.setText("귀하의 Id는 " + id + "입니다");
                mailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            redirectAttributes.addFlashAttribute("msg","false");
            return "redirect:/login/findIdInfo";
        }
        redirectAttributes.addFlashAttribute("msg","success");
        return "redirect:/login/findIdInfo";
    }

    @RequestMapping(value="/findPwd",method = RequestMethod.POST)
    public String findPwd(@ModelAttribute("userVO") UserVO userVO,RedirectAttributes redirectAttributes) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        result.put("id",userVO.getId());
        result.put("email",userVO.getEmail());
        String password = userService.findPwd(result);

        if(password != null) {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(new InternetAddress("gktgnjftm@naver.com","운영자"));
                messageHelper.setTo(new InternetAddress(userVO.getEmail(), "회원님"));
                messageHelper.setSubject("뚱석 홈페이지 패스워드 찾기 관련입니다.");
                messageHelper.setText("귀하의 패스워드는 " + password + "입니다");
                mailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            redirectAttributes.addFlashAttribute("msg","false");
            return "redirect:/login/findPwdInfo";
        }
        redirectAttributes.addFlashAttribute("msg","success");
        return "redirect:/login/findPwdInfo";

    }

    @RequestMapping(value = "/findIdinfo_phone",method = RequestMethod.POST)
    public String findIdinfo_phone(@ModelAttribute("userVO") UserVO userVO,RedirectAttributes redirectAttributes) throws Exception {
        HashMap<String,Object> result = new HashMap<>();
        if(cellPhonenumCheck(userVO.getCellphone()).equals("no")){
            redirectAttributes.addFlashAttribute("msg","wrongelement");
            return "redirect:/login/findIdInfo_phone";
        }
        else if(cellPhonelengthCheck(userVO.getCellphone()).equals("no")) {
            redirectAttributes.addFlashAttribute("msg","wronglength");
            return "redirect:/login/findIdInfo_phone";
        }
        else {
            result.put("cellphone", cellPhonelengthCheck(userVO.getCellphone()));
            String cellphone = userService.findIdinfo_phone(result);
            if ( cellphone == null ) {
                redirectAttributes.addFlashAttribute("msg","noelement");
                return "redirect:/login/findIdInfo_phone";
            }
            else {
                String api_key = "NCS7KU0EUXURFOJX";
                String api_secret = "EVKG8U0M8ULFD7H1C7DJ3EJUOOUSU8BK";
                int rand = (int) (Math.random() * 899999) + 100000;
                Coolsms coolsms = new Coolsms(api_key,api_secret);

                HashMap<String,Object> inserthashmap = new HashMap<>();
                inserthashmap.put("cellphone",cellphone);
                inserthashmap.put("sendnum",rand);
                userService.Insertcellnum(inserthashmap);

                HashMap<String,String> set = new HashMap<>();
                set.put("to",cellphone);
                set.put("from","028983724");
                set.put("text","뚱톡입니다. 인증번호는 ["+rand+"] 입니다.");
                set.put("type","sms");
                JSONObject jsonObject = coolsms.sendPostRequest("status",set);
                if( (boolean) jsonObject.get("status") ) {
                    System.out.println("성공");

                }
                else {
                    System.out.println("실패");
                }
            }
        }
        return "redirect:/user/findIdInfo_phone";
    }
    private String cellPhonenumCheck(String cellphone) throws Exception {
        for(int i = 0 ; i < cellphone.length() ; i++) {
            if(cellphone.charAt(i)=='-' || cellphone.charAt(i)>='0' && cellphone.charAt(i)<='9') continue;
            else {
                return "no";
            }
        }
        return "yes";
    }

    private String cellPhonelengthCheck(String cellphone) throws Exception {
        List<Character> list = new ArrayList<>();
        for(int i=0; i< cellphone.length();i++) {
            if(cellphone.charAt(i)=='-') continue;
            list.add(cellphone.charAt(i));
        }
        if(list.size()<10 || list.size()>11) return "no";
        else return list.toString();
    }

    private String emailCheck(String email) throws Exception{
        for(int i=0 ; i<email.length(); i++) {
            if(email.charAt(i)=='@') return "yes";
        }
        return "no";
    }
    @ResponseBody
    @RequestMapping(value = "/idCheck",method = RequestMethod.POST)
    public Map<String,Object> idCheck(@RequestBody UserVO userVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        String userVO1 = userService.getIdcheck(userVO.getId());
        if(userVO1 == null) {
            result.put("ret","실패");
        }
        else {
            result.put("ret","성공");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value="/emailCheck",method = RequestMethod.POST)
    public Map<String,Object> emailCheck(@RequestBody UserVO userVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        String userVO1 = userService.getemailCheck(userVO.getEmail());
        if(userVO1 == null) {
            result.put("ret","실패");
        }
        else {
            result.put("ret","성공");
        }
        return result;
    }


}
