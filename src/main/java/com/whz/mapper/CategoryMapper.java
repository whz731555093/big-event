package com.whz.mapper;

import com.whz.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
