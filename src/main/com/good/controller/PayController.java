package com.good.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pay")
public class PayController {

    @GetMapping("")
    public String goPayPage() {
        return "pay/samplePay";
    }

    @GetMapping("/user/mypage/charge/point")
    public @ResponseBody
    void chargePoint(Long amount) {
        System.out.println("전달된 금액 : " + amount);

    }
}
