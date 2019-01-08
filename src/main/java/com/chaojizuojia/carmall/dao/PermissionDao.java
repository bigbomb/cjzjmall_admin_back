package com.chaojizuojia.carmall.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * @author: ljj
 * @date: 2017/10/30 13:28
 */
public interface PermissionDao {
    /**
     * 查询用户的角色 菜单 权限
     *
     * @param username
     * @param roleid 
     * @return
     */
    JSONObject getUserPermission(String username, Integer roleid);

    /**
     * 查询所有的菜单
     *
     * @return
     */
    Set<String> getAllMenu();

    /**
     * 查询所有的权限
     *
     * @return
     */
    Set<String> getAllPermission();
}
