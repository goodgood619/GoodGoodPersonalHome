package com.good.dao;

import com.good.model.UserVO;

import java.util.HashMap;
import java.util.List;

public interface UserDAO {
    public List<UserVO> getUserList() throws Exception;
    public UserVO getUserInfo(String id) throws Exception;
    public int insertUser(UserVO userVO) throws Exception;
    public int updateUser(UserVO userVO) throws Exception;
    public int deleteUser(String id) throws Exception;
    public String getIdcheck(String id) throws Exception;
    public UserVO getLoginInfo(HashMap<String,Object> hashMap) throws Exception;
    public String sendIdcheck(HashMap<String,Object> hashMap) throws Exception;
    public String getemailCheck(String email) throws Exception;
    public String findPwd(HashMap<String,Object> hashMap) throws Exception;
    public String findIdinfo_phone(HashMap<String,Object> hashMap) throws Exception;
    public void Insertcellnum(HashMap<String,Object> hashMap) throws Exception;
}
