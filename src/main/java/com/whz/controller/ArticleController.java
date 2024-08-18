package com.whz.controller;

import com.whz.pojo.Article;
import com.whz.pojo.PageBean;
import com.whz.pojo.Result;
import com.whz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author whz
 * @description
 * @since 2024/8/13 21:36
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * @description 获取文章列表的两个版本
     * @return
     * @return Result<String>
     * @date
    **/
    // @RequestHeader(name = "Authorization")表示从请求头中获取Authorization的token
//    @GetMapping("/list")
//    public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response) {
//        try {
//            // 验证token
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有的文章数据...");
//        } catch(Exception e) {
//            // HTTP响应状态码为401
//            response.setStatus(401);
//            return Result.error("用户未登录");
//        }
//    }
    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("所有的文章数据...");
    }

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,    // 表示请求的url中，该参数非必须
                                          @RequestParam(required = false) String state) {   // 表示请求的url中，该参数非必须
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article article = articleService.findById(id);
        return Result.success(article);
    }
}
