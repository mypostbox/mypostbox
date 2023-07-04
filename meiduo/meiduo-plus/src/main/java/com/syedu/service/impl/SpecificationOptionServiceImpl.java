package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.SpecificationOption;
import com.syedu.domain.SpuSpecification;
import com.syedu.domain.Users;
import com.syedu.service.SpecificationOptionService;
import com.syedu.mapper.SpecificationOptionMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【tb_specification_option】的数据库操作Service实现
 * @createDate 2023-06-13 08:45:37
 */
@Service
public class SpecificationOptionServiceImpl extends ServiceImpl<SpecificationOptionMapper, SpecificationOption>
        implements SpecificationOptionService {
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    //分页查找options数据
    @Override
    public Map<String, Object> findAllOptionByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if (user.getId() != null) {
            map.put("lists", this.specificationOptionMapper.findAllOptionByPage((page - 1) * pageSize, pageSize));
            map.put("page", page);
            Long total = this.specificationOptionMapper.selectCount(null);
            map.put("pages", Math.ceil(Double.parseDouble(Long.toString(total)) / Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }

    //添加保存options
    @Override
    public Integer saveOption(String token, SpecificationOption specificationOption) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if (user.getId() != null) {
            return this.specificationOptionMapper.insert(specificationOption);
        }
        return null;
    }

    //根据optionId获取option
    @Override
    public Map<String, Object> getOption(String token, Integer optionId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if (user.getId() != null) {
            return this.specificationOptionMapper.getOption(optionId);
        }
        return null;
    }

    //根据optionsId修改option
    @Override
    public Integer updateOption(String token, Integer optionId, SpecificationOption specificationOption) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if (user.getId() != null) {
            specificationOption.setId(optionId);
            return this.specificationOptionMapper.updateById(specificationOption);
        }
        return null;
    }
    //根据optionsId删除option
    @Override
    public Integer deleteOption(String token, Integer optionId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.specificationOptionMapper.deleteById(optionId);
        }
        return null;
    }

}




