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
    public String getIdcheck(String id) throws Exception {
        return userDAO.getIdcheck(id);
    }

    @Override
    public UserVO getLoginInfo(HashMap<String, Object> hashMap) throws Exception {
        return userDAO.getLoginInfo(hashMap);
    }

    @Override
    public String sendIdcheck(HashMap<String, Object> hashMap) throws Exception {
        return userDAO.sendIdcheck(hashMap);
    }

    @Override
    public String getemailCheck(String email) throws Exception {
        return userDAO.getemailCheck(email);
    }

    @Override
    public String findPwd(HashMap<String, Object> hashMap) throws Exception {
        return userDAO.findPwd(hashMap);
    }

    @Override
    public String findIdinfo_phone(HashMap<String,Object> hashMap) throws Exception {
        return userDAO.findIdinfo_phone(hashMap);
    }

    @Override
    public void Insertcellnum(HashMap<String, Object> hashMap) throws Exception {
        userDAO.Insertcellnum(hashMap);
    }

    @Override
    public UserVO getMemberInfo(HashMap<String, Object> hashMap) throws Exception {
        return userDAO.getMemberInfo(hashMap);
    }

    @Override
    public void updateMember(HashMap<String,Object> hashMap) throws Exception {
        userDAO.updateMember(hashMap);
    }
}
