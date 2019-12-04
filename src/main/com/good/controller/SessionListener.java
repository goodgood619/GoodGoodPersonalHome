package com.good.controller;

import com.good.model.UserVO;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Hashtable;

public class SessionListener {
    //싱글톤을 쓸것이기 때문에 static을 제한적으로 사용!!
    private static SessionListener sessionListener = null;

    private static Hashtable<Object, Object> loginusers = new Hashtable<>();

    public static synchronized SessionListener getInstance() {
        if(sessionListener == null){
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }

    //ID추가
    public void valueBound(HttpSession httpSession){
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        loginusers.put(userVO.getId(),"login");
        System.out.println(httpSession.getId()+ "로그인 완료!");
    }

    //ID제거
    public void valueUnbound(HttpSession httpSession) {
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        loginusers.remove(userVO.getId());
        System.out.println(httpSession.getId() + "로그아웃 완료!");
    }

    public boolean isUsing(String userid){
        return loginusers.containsKey(userid);
    }
}
