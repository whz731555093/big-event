package com.whz.service.impl;

import com.whz.mapper.CategoryMapper;
import com.whz.pojo.Category;
import com.whz.service.CategoryService;
import com.whz.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author MyPC
 * @description
 * @since 2024/8/16 21:57
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        category.setCreateUser(userId);

        categoryMapper.add(category);
    }
}
