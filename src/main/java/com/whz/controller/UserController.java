package com.whz.controller;

import com.whz.pojo.Result;
import com.whz.pojo.User;
import com.whz.service.UserService;
import com.whz.utils.JwtUtil;
import com.whz.utils.Md5Util;
import com.whz.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author whz
 * @description
 * @since 2024/8/11 22:24
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @description 用户注册
     *
     * @param userName
     * @param password
     * @return
     * @return com.whz.pojo.Result
     * @date
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String userName,
           @Pattern(regexp = "^\\S{5,16}$")String password) {
        User u = userService.findByUserName(userName);
        // 查询用户是否存在
        if(u == null) {
            //没被占用 注册
            userService.register(userName, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    /**
     * @description 用户登录
     *
     * @param userName
     * @param password
     * @return
     * @return com.whz.pojo.Result<java.lang.String>
     * @date
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String userName,
            @Pattern(regexp = "^\\S{5,16}$")String password) {
        // 根据用户名查询用户
        User loginUser = userService.findByUserName(userName);
        // 判断该用户是否存在
        if(loginUser == null) {
            return Result.error("用户名错误");
        }
        // 判断密码是否正确 注意需要比较的是加密的密码
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登录成功 并生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    /**
     * @description 获取用户信息列表的两个版本
     * @return
     * @return Result<User>
     * @date
     **/
//    @GetMapping("/userInfo")
//    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
//        // 根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String userName = (String)map.get("username");
//
//        User user = userService.findByUserName(userName);
//        return Result.success(user);
//    }
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String userName = (String)map.get("username");  // 对应表中的username列
        User user = userService.findByUserName(userName);
        return Result.success(user);
    }
}
