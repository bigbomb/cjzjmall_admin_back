package com.chaojizuojia.carmall.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.chaojizuojia.carmall.util.PasswordUtils;


public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {  
    @Override  
       public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {  
           UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
 
           Object tokenCredentials = PasswordUtils.encryptPassword(String.valueOf(token.getPassword()));  
           Object accountCredentials = getCredentials(info);  
           //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
           return equals(tokenCredentials, accountCredentials);  
       }  
 
      
}  