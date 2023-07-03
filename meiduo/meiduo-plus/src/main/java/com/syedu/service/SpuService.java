package com.syedu.service;

import com.syedu.domain.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_spu】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SpuService extends IService<Spu> {

        List<Spu> findSpu(String token) throws Exception;
        //分页查找spu数据
        Map<String,Object> findAllSpuByPage(String token,Integer page,Integer pageSize) throws Exception;
}
