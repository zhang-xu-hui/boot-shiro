package com.zxh.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class UserRealm extends AuthorizingRealm {

    @Resource
    private HttpServletRequest request;

    /**
     * 执行授权逻辑(权限判断)
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //进行资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源授权字符串
        info.addStringPermission("test:aaa");
        return info;
    }

    /**
     * 执行认证逻辑(登录认证)
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        String userName = "admin";
        String password = "123456";
        //判断用户名和密码
        //1.判断用户名，用户名不存在则返回空，捕捉UnknownAccountException
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (!token.getUsername().equals(userName))
            return null;
        //2.判断密码，捕捉IncorrectCredentialsException
        return new SimpleAuthenticationInfo("", password, "");
    }
}
