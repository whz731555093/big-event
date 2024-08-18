package com.whz.service;

import com.whz.pojo.Article;
import com.whz.pojo.PageBean;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 10:50
 */
public interface ArticleService {
    // 添加文章
    void add(Article article);

    // 条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 获取文章详情
    Article findById(Integer id);

    // 更新文章
    void update(Article article);

    // 删除文章
    void delete(Integer id);
}
