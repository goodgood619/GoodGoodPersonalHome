package com.good.dao;

import com.good.model.UserVO;

import java.util.List;

public interface UserDAO {
    public List<UserVO> getUserList() throws Exception;
    public UserVO getUserInfo(String id) throws Exception;
    public int insertUser(UserVO userVO) throws Exception;
    public int updateUser(UserVO userVO) throws Exception;
    public int deleteUser(String id) throws Exception;
}