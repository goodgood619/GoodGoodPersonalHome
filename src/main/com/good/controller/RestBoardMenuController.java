package com.good.controller;

import com.good.model.MenuVO;
import com.good.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restmenu")
public class RestBoardMenuController {

    private MenuService menuService;

    @Inject
    public RestBoardMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value="/getMenuList",method = RequestMethod.POST)
    public Map<String,Object> getMenuList() throws Exception {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("menuList",menuService.getMenuList());
            result.put("status","성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","실패");
        }

        return result;
    }

    @RequestMapping(value = "/saveMenu",method = RequestMethod.POST)
    public Map<String,Object> saveMenu(MenuVO menuvo) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try {
            menuService.saveMenu(menuvo);
            result.put("status","성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","실패");
        }
        return result;
    }

    @RequestMapping(value = "/updateMenu",method = RequestMethod.POST)
    public Map<String,Object> updateMenu(MenuVO menuVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try {
            menuService.updateMenu(menuVO);
            result.put("status","성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","실패");
        }
        return result;
    }

    @RequestMapping(value = "/deleteMenu",method = RequestMethod.POST)
    public Map<String,Object> deleteMenu(@RequestParam("code") String code) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try {
            menuService.deleteMenu(code);
            result.put("status","성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","실패");
        }
        return result;
    }
}
