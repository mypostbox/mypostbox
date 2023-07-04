package com.syedu.controller.admins;

import com.syedu.domain.AuthGroup;
import com.syedu.domain.AuthPermission;
import com.syedu.service.AuthGroupService;
import com.syedu.service.AuthPermissionService;
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
                           AuthPermission authPermission) throws Exception {
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
                             @PathVariable("permissionId") Integer permissionId){
        return null;
    }

    //用户组管理
    //分页获取（auth_group）中的数据
    @GetMapping("groups")
    Map<String,Object> findAllGroupByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.authGroupService.findAllGroupByPage(token, page, pageSize);
    }
    //获取（auth_group）的所有数据
    @GetMapping("simple")
    List<Map<String,Object>> findAllGroup(@RequestHeader("Authorization") String token) throws Exception {
        return this.authGroupService.findAllGroup(token);
    }
    //添加（auth_group）数据
    @PostMapping("groups")
    Integer saveGroup(@RequestHeader("Authorization") String token){
        return null;
    }
}
