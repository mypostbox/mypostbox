package com.syedu.mapper;

import com.syedu.domain.TbContentCategory;
import com.syedu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/813:44
 */
public class TbContentCategroyMapperTest {

    private SqlSession session = MybatisUtils.getSqlSession();

    @Test
    public void test(){
        TbContentCategoryMapper mapper = this.session.getMapper(TbContentCategoryMapper.class);
        List<TbContentCategory> allContentCategory = mapper.getAllContentCategory();
        allContentCategory.stream().forEach(System.out::println);
    }
}
