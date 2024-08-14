package com.whz.controller;

import com.whz.pojo.Result;
import com.whz.pojo.User;
import com.whz.service.UserService;
import com.whz.utils.JwtUtil;
import com.whz.utils.Md5Util;
import com.whz.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * @description 更新用户数据
     *
     * @param user @RequestBody表示读取请求集的数据转换为JSON对象
     * @return
     * @return Result
     * @date
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * @description
     *
     * @param avatarUrl 利用@URL可以限定传入参数为URL格式
     * @return
     * @return Result
     * @date
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * @description 更新密码
     *
     * @param params
     * @return
     * @return com.whz.pojo.Result
     * @date
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        // 校验参数
        String oldPassword = params.get("old_pwd");
        String newPassword = params.get("new_pwd");
        String confirmPassword = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPassword)
                || !StringUtils.hasLength(newPassword)
                || !StringUtils.hasLength(confirmPassword)) {
            return Result.error("缺少必要的参数");
        }

        // 原密码是否正确
        // 调用userService根据用户名拿到原密码，再与oldPassword比较
        Map<String, Object> map = ThreadLocalUtil.get();
        String userName = (String)map.get("username");
        User loginUser = userService.findByUserName(userName);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPassword))) {
            return Result.error("原密码错误");
        }

        // newPassword与confirmPassword是否一致
        if (!confirmPassword.equals(newPassword)) {
            return Result.error("两次输入的新密码不一致");
        }

        // 调用userService完成更新
        userService.updatePwd(newPassword);
        return Result.success();
    }
}
