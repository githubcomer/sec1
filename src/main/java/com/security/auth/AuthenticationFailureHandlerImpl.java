package com.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 用户登录认证失败后
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //AuthenticationException 存放着异常信息，获取出来，放到 Request中，转发到登录页面。
        request.setAttribute("error", exception.getMessage());
        request.getRequestDispatcher("/auth/login?error=true").forward(request, response);
    }

}