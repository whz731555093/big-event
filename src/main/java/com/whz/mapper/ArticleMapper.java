package com.whz.mapper;

import com.whz.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 10:51
 */
@Mapper
public interface ArticleMapper {
    // 添加文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)"
        + " values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    // 动态sql，最好用映射配置文件的方式
    List<Article> list(Integer userId, Integer categoryId, String state);

    // 查询文章
    @Select("select * from article where id = #{id}")
    Article select(Integer id);

}
