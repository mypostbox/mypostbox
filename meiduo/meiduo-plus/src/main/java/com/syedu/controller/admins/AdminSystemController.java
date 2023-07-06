package com.syedu.controller.admins;

import com.syedu.domain.*;
import com.syedu.service.AuthGroupService;
import com.syedu.service.AuthPermissionService;
import com.syedu.service.UsersUserPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/417:37
 */
@RestController
@RequestMapping("admins/permission")
public class AdminSystemController {
    @Autowired
    private AuthPermissionService authPermissionService;
    @Autowired
    private AuthGroupService authGroupService;
    @Autowired
    private UsersUserPermissionsService usersUserPermissionsService;
    //权限管理
    //分页获取权限数据(auth_permission)
    @GetMapping("perms")
    Map<String,Object> findAllPermissionByPage(@RequestHeader("Authorization") String token,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.authPermissionService.findAllPermissionByPage(token, page, pageSize);
    }
    //--------------缺少django_content_type表，直接拿id代替主数据----------------------
    //获取所有的权限类型
    @GetMapping("content_types")
    List<Map<String,Object>> findAllContentType(@RequestHeader("Authorization") String token) throws Exception {
        return this.authPermissionService.findAllContentType(token);
    }
    //添加保存权限
    @PostMapping("perms")
    Integer savePermission(@RequestHeader("Authorization") String token,
                           @RequestBody AuthPermission authPermission) throws Exception {
        return this.authPermissionService.savePermission(token, authPermission);
    }

    //根据id获取当前权限
    @GetMapping("perms/{permissionId}")
    AuthPermission findPermission(@RequestHeader("Authorization") String token,
                                  @PathVariable("permissionId") Integer permissionId) throws Exception {
        return this.authPermissionService.findPermission(token, permissionId);
    }
    //根据id修改权限
    @PutMapping("perms/{permissionId}")
    Integer updatePermission(@RequestHeader("Authorization") String token,
                             @PathVariable("permissionId") Integer permissionId,
                             @RequestBody AuthPermission authPermission) throws Exception {
        return this.authPermissionService.updatePermission(token, permissionId, authPermission);
    }
    //根据id删除权限
    @DeleteMapping("perms/{permissionId}")
    Integer deletePermission(@RequestHeader("Authorization") String token,
                             @PathVariable("permissionId") Integer permissionId) throws Exception {
        return this.authPermissionService.deletePermission(token, permissionId);
    }

    //用户组管理
    //分页获取用户(角色)
    @GetMapping("groups")
    Map<String,Object> findAllGroupByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.authGroupService.findAllGroupByPage(token, page, pageSize);
    }
    //获取所有的权限
    @GetMapping("simple")
    List<Map<String,Object>> findAllGroup(@RequestHeader("Authorization") String token) throws Exception {
        return this.authPermissionService.findAllGroup(token);
    }
    //添加角色
    @PostMapping("groups")
    Integer saveGroup(@RequestHeader("Authorization") String token,
                      @RequestBody MyGroupBody permissions) throws Exception {
        return this.authGroupService.saveGroup(token, permissions);
    }

    //根据id获取角色的名称和权限
    @GetMapping("groups/{groupId}")
    MyGroupBody findGroup(@RequestHeader("Authorization") String token,
                                 @PathVariable("groupId") Integer groupId) throws Exception {
        return this.authGroupService.findGroup(token, groupId);
    }
    //根据id修改角色
    @PutMapping("groups/{groupId}")
    Integer updateGroup(@RequestHeader("Authorization") String token,
                        @PathVariable("groupId") Integer groupId,
                        @RequestBody MyGroupBody permissions) throws Exception {
        return this.authGroupService.updateGroup(token, groupId, permissions);
    }
    //根据id删除角色
    @DeleteMapping("groups/{groupId}")
    Integer deleteGroup(@RequestHeader("Authorization") String token,
                        @PathVariable("groupId") Integer groupId) throws Exception {
        return this.authGroupService.deleteGroup(token, groupId);
    }

    //管理员管理
    //分页获取管理员的信息
    @GetMapping("admin")
    Map<String,Object> findAllAdminByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.usersUserPermissionsService.findAllAdminByPage(token, page, pageSize);
    }
    //获取所有的用户组
    @GetMapping("admin/simple")
    List<AuthGroup> findAllAuthGroup(@RequestHeader("Authorization") String token) throws Exception {
        return this.authGroupService.findAllAuthGroup(token);
    }
    //添加管理员
    @PostMapping("admin")
    Integer saveAdmin(@RequestHeader("Authorization") String token,
                      @RequestBody MyUserBody myUserBody) throws Exception {
        return this.usersUserPermissionsService.saveAdmin(token, myUserBody);
    }
    //根据id获取管理员信息
    @GetMapping("admin/{userId}")
    MyUserBody getAdmin(@RequestHeader("Authorization") String token,
                        @PathVariable("userId") Integer userId) throws Exception {
        return this.usersUserPermissionsService.getAdmin(token, userId);
    }
    //根据id修改管理员信息
    @PutMapping("admin/{userId}")
    Integer updateAdmin(@RequestHeader("Authorization") String token,
                        @PathVariable("userId") Integer userId,
                        @RequestBody MyUserBody myUserBody) throws Exception {
        return this.usersUserPermissionsService.updateAdmin(token, userId, myUserBody);
    }
    //根据id删除管理员
    @DeleteMapping("admin/{userId}")
    Integer deleteAdmin(@RequestHeader("Authorization") String token,
                        @PathVariable("userId") Integer userId) throws Exception {
        return this.usersUserPermissionsService.deleteAdmin(token, userId);
    }

}
