package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.AuthPermission;
import com.syedu.service.AuthPermissionService;
import com.syedu.mapper.AuthPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【auth_permission】的数据库操作Service实现
* @createDate 2023-06-13 08:45:36
*/
@Service
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermission>
    implements AuthPermissionService{

}




