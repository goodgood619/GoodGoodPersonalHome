package com.good.dao;

import com.good.model.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Inject
    private SqlSession sqlSession;
    @Override
    public List<UserVO> getUserList() throws Exception {
        return sqlSession.selectList("mapper.boarduserMapper.getUserList");
    }

    @Override
    public UserVO getUserInfo(String id) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.getUserInfo",id);
    }

    @Override
    public int insertUser(UserVO userVO) throws Exception {
        return sqlSession.insert("mapper.boarduserMapper.insertUser",userVO);
    }

    @Override
    public int updateUser(UserVO userVO) throws Exception {
        return sqlSession.update("mapper.boarduserMapper.updateUser",userVO);
    }

    @Override
    public int deleteUser(String id) throws Exception {
        return sqlSession.delete("mapper.boarduserMapper.deleteUser",id);
    }

    @Override
    public UserVO getLoginInfo(HashMap<String,Object> map) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.getLoginInfo",map);
    }
}
