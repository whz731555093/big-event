package com.whz.mapper;

import com.whz.pojo.Category;
import org.apache.ibatis.annotations.*;

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

    // 更新分类
    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    // 删除分类
    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
