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

        //根据id获取spu信息
        Map<String,Object> findSpuById(String toKen,Integer spuId) throws Exception;


        //保存spu
        Integer saveSpu(String token,Spu spu) throws Exception;
        //修改spu
        Integer updateSpu(String token,Integer spuId,Spu spu) throws Exception;
        //删除spu
        Integer deleteSpu(String token,Integer spuId) throws Exception;
}
