package com.good.controller;

import com.good.domain.PracticeProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/*")
public class TestController {

    @GetMapping("oktest")
    public @ResponseBody String homeTest(){return "Spring+maven";}


    @GetMapping("doC")
    public String doC(@ModelAttribute("msg") String msg){
        return "result";
    }

    @GetMapping("doD")
    public ModelAndView doD(){
        ModelAndView modelAndView = new ModelAndView("PracticeProductSample");
        PracticeProductVO practiceProductVO = new PracticeProductVO("Notebook",1000);
        modelAndView.addObject("practiceProductVO",practiceProductVO);
        return modelAndView;
    }

    @GetMapping("doE")
    @ResponseBody
    public PracticeProductVO doJson(){
        return new PracticeProductVO("laptop",3000000);
    }

}
