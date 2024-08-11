package com.whz.service;

import com.whz.pojo.User;

/**
 * @author whz
 * @description
 * @since 2024/8/11 22:22
 */
public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String userName);

    // 注册
    void register(String userName, String password);
}
