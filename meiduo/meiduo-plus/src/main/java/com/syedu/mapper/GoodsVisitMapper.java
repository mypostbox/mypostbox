package com.syedu.mapper;

import com.syedu.domain.GoodsVisit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_visit】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.GoodsVisit
*/
public interface GoodsVisitMapper extends BaseMapper<GoodsVisit> {
    /**
     * 根据count和categoryId向tb_goods_Visit表加入数据
     * @param count
     * @param categoryId
     * @return
     */
    @Insert("insert into tb_goods_visit(count,date,category_id) values(#{count},DATE_FORMAT(CURDATE(),'%Y-%m-%d'),#{categoryId})")
    Integer InsertGoodsVisit(@Param("count") Integer count,@Param("categoryId") Integer categoryId);

    /**
     * 查询每天商品种类的访问量
     * @return
     */
    @Select("SELECT count,category_id FROM tb_goods_visit WHERE DATEDIFF(CURDATE(),date) = 0")
    @Results({
            @Result(property = "count" , column = "count"),
            @Result(property = "category" , column = "category_id" ,
                    one = @One(select = "com.syedu.mapper.GoodsCategoryMapper.findNameById"))
    })
    List<Map<String,Object>> findAllGoodsDayViews();
}




