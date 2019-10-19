package com.good.dao;

import com.good.model.MenuVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MenuDAO {
    public List<MenuVO> getMenuList() throws Exception;
    public int saveMenu(MenuVO menuVO) throws Exception;
    public int updateMenu(MenuVO menuVO) throws Exception;
    public int deleteMenu(String code) throws Exception;
}
