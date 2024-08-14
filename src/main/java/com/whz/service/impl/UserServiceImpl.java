package com.whz.service.impl;

import com.whz.mapper.UserMapper;
import com.whz.pojo.User;
import com.whz.service.UserService;
import com.whz.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author whz
 * @description
 * @since 2024/8/11 22:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @description 根据用户名查询用户
     *
     * @param userName
     * @return
     * @return com.whz.pojo.User
     * @date
     */
    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    /**
     * @description 注册
     *
     * @param userName
     * @param password
     * @return
     * @date
     */
    @Override
    public void register(String userName, String password) {
        // 加密
        String md5String = Md5Util.getMD5String(password);

        // 添加
        userMapper.add(userName, md5String);
    }
}
