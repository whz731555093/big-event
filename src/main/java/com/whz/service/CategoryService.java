package com.whz.service;

import com.whz.pojo.Category;

import java.util.List;

/**
 * @author MyPC
 * @description
 * @since 2024/8/16 21:57
 */
public interface CategoryService {
    // 新增分类
    void add(Category category);

    // 列表查询
    List<Category> list();

    // 根据ID查询分类信息
    Category findById(Integer id);

    // 更新分类
    void update(Category category);

    // 删除分类
    void delete(Integer id);
}
