package com.good.dao;

import com.good.model.MenuVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class MenuDAOImpl implements MenuDAO{

    private SqlSession sqlSession;

    @Inject
    public MenuDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<MenuVO> getMenuList() throws Exception {
        return sqlSession.selectList("mapper.boardmenuMapper.getMenuList");
    }

    @Override
    public int saveMenu(MenuVO menuVO) throws Exception {
        return sqlSession.insert("mapper.boardmenuMapper.insertMenu",menuVO);
    }

    @Override
    public int updateMenu(MenuVO menuVO) throws Exception {
        return sqlSession.update("mapper.boardmenuMapper.updateMenu",menuVO);
    }

    @Override
    public int deleteMenu(String code) throws Exception {
        return sqlSession.delete("mapper.boardmenuMapper.deleteMenu",code);
    }
}
