package com.good.service;

import com.good.model.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> getUserList() throws Exception;
    public UserVO getUserInfo(String id) throws Exception;
    public void insertUser(UserVO userVO) throws Exception;
    public void updateUser(UserVO userVO) throws Exception;
    public void deleteUser(String id) throws Exception;
}
