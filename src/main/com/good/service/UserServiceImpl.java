package com.good.service;

import com.good.dao.UserDAO;
import com.good.model.UserVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public List<UserVO> getUserList() throws Exception {
        return userDAO.getUserList();
    }

    @Override
    public UserVO getUserInfo(String id) throws Exception {
        return userDAO.getUserInfo(id);
    }

    @Override
    public void insertUser(UserVO userVO) throws Exception {
        userDAO.insertUser(userVO);
    }

    @Override
    public void updateUser(UserVO userVO) throws Exception {
        userDAO.updateUser(userVO);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        userDAO.deleteUser(id);
    }

    @Override
    public UserVO getLoginInfo(HashMap<String, Object> hashMap) throws Exception {
        return userDAO.getLoginInfo(hashMap);
    }
}
