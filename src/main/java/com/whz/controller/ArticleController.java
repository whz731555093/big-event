package com.whz.controller;

import com.whz.pojo.Result;
import com.whz.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Map;

/**
 * @author whz
 * @description
 * @since 2024/8/13 21:36
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

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
}
