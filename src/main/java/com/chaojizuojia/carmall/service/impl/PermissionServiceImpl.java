package com.chaojizuojia.carmall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chaojizuojia.carmall.dao.PermissionDao;
import com.chaojizuojia.carmall.service.PermissionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: ljj
 * @description:
 * @date: 2017/10/30 13:15
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @return
     */
    @Override
    public JSONObject getUserPermission(String username,String ids) {
        JSONObject userPermission = getUserPermissionFromDB(username,ids);
        return userPermission;
    }

    /**
     * 从数据库查询用户权限信息
     *
     * @param username
     * @param id 
     * @return
     */
    private JSONObject getUserPermissionFromDB(String username, String ids) {
    	String[] roleIds = ids.split(",");
    	List<String> roleIdsList= Arrays.asList(roleIds);
        JSONObject userPermission = new JSONObject();
        Set<String> menuList = new TreeSet<String>();
        Set<String> permissionList = new TreeSet<String>();
        List<String> newMenuList = new ArrayList<String>();
        List<String> newPermissionListList = new ArrayList<String>();
        for(String roleId:roleIds)
        {
        	
        	  userPermission = permissionDao.getUserPermission(username,Integer.parseInt(roleId));
        	  //管理员角色ID为1
              if (roleIdsList.contains("1")) {
                  //查询所有菜单  所有权限
                  menuList = permissionDao.getAllMenu();
                  permissionList = permissionDao.getAllPermission();
                  userPermission.put("menuList", menuList);
                  userPermission.put("permissionList", permissionList);
                  System.out.println(userPermission.toString());
                  return userPermission;
              }
              else
              {
            	  String menuListStr = userPermission.getString("menuList").replace("[","").replace("]","").replace(" ", "");
            	  String permissionListStr = userPermission.getString("permissionList").replace("[","").replace("]","").replace(" ","");
            	  String[] menuListArr = menuListStr.split(","); // 用,分割
            	  String[] permissionListArr = permissionListStr.split(","); // 用,分割
            	  newMenuList = Arrays.asList(menuListArr);
            	  newPermissionListList = Arrays.asList(permissionListArr);
            	  menuList.addAll(newMenuList);
            	  permissionList.addAll(newPermissionListList);
              }
      
        }
  	  	userPermission.put("menuList", menuList);
  	  	userPermission.put("permissionList", permissionList);
  	  	System.out.println(userPermission.toString());
        return userPermission;
    }
}
