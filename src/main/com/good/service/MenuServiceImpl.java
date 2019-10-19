package com.good.service;

import com.good.dao.MenuDAO;
import com.good.model.MenuVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Inject
    private MenuDAO menuDAO;

    @Override
    public List<MenuVO> getMenuList() throws Exception {
        return menuDAO.getMenuList();
    }

    @Override
    public void saveMenu(MenuVO menuVO) throws Exception {
        menuDAO.saveMenu(menuVO);
    }

    @Override
    public void updateMenu(MenuVO menuVO) throws Exception {
        menuDAO.updateMenu(menuVO);
    }

    @Override
    public void deleteMenu(String code) throws Exception {
        menuDAO.deleteMenu(code);
    }
}
