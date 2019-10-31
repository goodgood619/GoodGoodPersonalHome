package com.good.service;

import com.good.model.MenuVO;

import java.util.List;

public interface MenuService {
    List<MenuVO> getMenuList() throws Exception;
    void saveMenu(MenuVO menuVO) throws Exception;
    void updateMenu(MenuVO menuVO) throws Exception;
    void deleteMenu(String code) throws Exception;
}
