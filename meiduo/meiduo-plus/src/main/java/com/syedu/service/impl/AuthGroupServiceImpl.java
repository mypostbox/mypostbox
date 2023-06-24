package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.AuthGroup;
import com.syedu.service.AuthGroupService;
import com.syedu.mapper.AuthGroupMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【auth_group】的数据库操作Service实现
* @createDate 2023-06-13 08:45:36
*/
@Service
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupMapper, AuthGroup>
    implements AuthGroupService{

}




