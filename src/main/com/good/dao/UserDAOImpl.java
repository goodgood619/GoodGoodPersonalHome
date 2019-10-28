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
    public String getIdcheck(String id) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.getIdcheck",id);
    }

    @Override
    public UserVO getLoginInfo(HashMap<String,Object> map) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.getLoginInfo",map);
    }

    @Override
    public String sendIdcheck(HashMap<String, Object> hashMap) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.sendIdFind",hashMap);
    }

    @Override
    public String getemailCheck(String email) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.getemailCheck",email);
    }

    @Override
    public String findIdinfo_phone(HashMap<String,Object> hashMap) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.findIdinfophone",hashMap);
    }

    @Override
    public void Insertcellnum(HashMap<String, Object> hashMap) throws Exception {
        sqlSession.insert("mapper.boarduserMapper.Insertcellnum",hashMap);
    }

    @Override
    public String findPwd(HashMap<String, Object> hashMap) throws Exception {
        return sqlSession.selectOne("mapper.boarduserMapper.findPwd",hashMap);
    }
}
