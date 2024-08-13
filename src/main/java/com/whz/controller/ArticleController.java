package com.whz.controller;

import com.whz.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whz
 * @description
 * @since 2024/8/13 21:36
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    public Result<String> list() {
        return Result.success("所有的文章数据...");
    }
}
