package com.good.dao;

import com.good.model.UserVO;

import java.util.HashMap;
import java.util.List;

public interface UserDAO {
    List<UserVO> getUserList() throws Exception;
    UserVO getUserInfo(String id) throws Exception;
    int insertUser(UserVO userVO) throws Exception;
    int updateUser(UserVO userVO) throws Exception;
    int deleteUser(String id) throws Exception;
    String getIdcheck(String id) throws Exception;
    UserVO getLoginInfo(HashMap<String,Object> hashMap) throws Exception;
    String sendIdcheck(HashMap<String,Object> hashMap) throws Exception;
    String getemailCheck(String email) throws Exception;
    String findPwd(HashMap<String,Object> hashMap) throws Exception;
    String findIdinfo_phone(HashMap<String,Object> hashMap) throws Exception;
    void Insertcellnum(HashMap<String,Object> hashMap) throws Exception;
    UserVO getMemberInfo(HashMap<String, Object> hashMap) throws Exception;
    void updateMember(HashMap<String,Object> hashMap);
}
