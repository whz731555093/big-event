package com.whz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whz.mapper.ArticleMapper;
import com.whz.pojo.Article;
import com.whz.pojo.PageBean;
import com.whz.service.ArticleService;
import com.whz.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        // 开启分页查询 导入PageHelper插件
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        // 调用mapper
        List<Article> articles = articleMapper.list(userId, categoryId, state);
        // Page中提供了方法，可获取PageHelper分页查询后，得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>)articles;

        // 把数据填充到PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.select(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
