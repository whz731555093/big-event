package com.whz.service;

import com.whz.pojo.User;

/**
 * @author whz
 * @description
 * @since 2024/8/11 22:22
 */
public interface UserService {

    /**
     * @description 根据用户名查询用户
     *
     * @param userName
     * @return
     * @return com.whz.pojo.User
     * @date
     */
    User findByUserName(String userName);

    /**
     * @description 注册
     *
     * @param userName
     * @param password
     * @return
     * @date
     */
    void register(String userName, String password);
}
