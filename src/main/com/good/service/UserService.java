package com.good.service;

import com.good.model.UserVO;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<UserVO> getUserList() throws Exception;
    UserVO getUserInfo(String id) throws Exception;
    void insertUser(UserVO userVO) throws Exception;
    void updateUser(UserVO userVO) throws Exception;
    void deleteUser(String id) throws Exception;
    String getIdcheck(String id) throws Exception;
    UserVO getLoginInfo(HashMap<String,Object> hashMap) throws Exception;
    String sendIdcheck(HashMap<String,Object> hashMap) throws Exception;
    String getemailCheck(String email) throws Exception;
    String findPwd(HashMap<String,Object> hashMap) throws Exception;
    String findIdinfo_phone(HashMap<String,Object> hashMap) throws Exception;
    void Insertcellnum(HashMap<String,Object> hashMap) throws Exception;
    UserVO getMemberInfo(HashMap<String, Object> hashMap) throws Exception;
    void updateMember(HashMap<String,Object> hashMap) throws Exception;
}
