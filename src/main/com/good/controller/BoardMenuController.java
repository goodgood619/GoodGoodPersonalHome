package com.good.controller;

import com.good.model.MenuVO;
import com.good.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/menu")
public class BoardMenuController {

    @RequestMapping(value="/getMenu",method = RequestMethod.GET)
    public String getMenuList(Model model) throws Exception{
        model.addAttribute("menuVO",new MenuVO());
        return "/menu/menu";
    }

}
