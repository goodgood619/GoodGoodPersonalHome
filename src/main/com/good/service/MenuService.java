package com.good.service;

import com.good.model.MenuVO;

import java.util.List;

public interface MenuService {
    public List<MenuVO> getMenuList() throws Exception;
    public void saveMenu(MenuVO menuVO) throws Exception;
    public void updateMenu(MenuVO menuVO) throws Exception;
    public void deleteMenu(String code) throws Exception;
}
