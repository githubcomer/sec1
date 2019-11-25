package com.security.auth;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.dbp.dao.SysUserMapper;
import com.dbp.model.SysUser;

/**
 * 登录认证成功后
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Resource
    private SysUserMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	//UserDetails 中存放着用户名等信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //获取该用户信息，根据自己的业务规则写
        SysUser sysuser = this.mapper.selectByUsername(userDetails.getUsername());
        //将用户放到 Session
        request.getSession().setAttribute("_sysuser", sysuser);
        //跳转到主页
        response.sendRedirect(request.getContextPath() + "/main/common");
    }

}
