package com.whz.service.impl;

import com.whz.mapper.ArticleMapper;
import com.whz.pojo.Article;
import com.whz.service.ArticleService;
import com.whz.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 10:50
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // 补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }
}
