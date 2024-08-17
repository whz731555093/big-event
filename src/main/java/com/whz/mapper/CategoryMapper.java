package com.whz.mapper;

import com.whz.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MyPC
 * @description
 * @since 2024/8/16 21:58
 */
@Mapper
public interface CategoryMapper {
    // 添加分类
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)"
            + " values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    // 查询所有
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    // 根据ID查询分类
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);
}
