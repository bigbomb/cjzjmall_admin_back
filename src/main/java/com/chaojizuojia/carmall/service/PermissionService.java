package com.chaojizuojia.carmall.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: ljj
 * @date: 2017/10/30 13:10
 */
public interface PermissionService {
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @param roleIds 
     * @return
     */
    JSONObject getUserPermission(String username, String roleIds);
}
