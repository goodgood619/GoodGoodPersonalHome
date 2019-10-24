package com.good.interceptor;

import com.good.model.UserVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception{
        HttpSession httpSession = req.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("member");

        if (userVO == null) {
            res.sendRedirect("/login/doinitLogin");
            return false;
        }

        return true;
    }
}
