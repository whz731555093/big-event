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
     * @param username
     * @return
     * @return com.whz.pojo.User
     * @date
     */
    User findByUserName(String username);

    /**
     * @description 注册
     *
     * @param userName
     * @param password
     * @return
     * @date
     */
    void register(String userName, String password);

    /**
     * @description 更新用户信息
     *
     * @param user
     * @return
     * @date
     */
    void update(User user);

    /**
     * @description 更新头像
     *
     * @param avatarUrl
     * @return
     * @date
     */
    void updateAvatar(String avatarUrl);

    /**
     * @description 更新密码
     *
     * @param newPassword
     * @return
     * @date
     */
    void updatePwd(String newPassword);
}
