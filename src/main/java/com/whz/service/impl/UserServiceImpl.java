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

    @Override
    public User findByUserName(String userName) {
        User u = userMapper.findByUserName(userName);
        return u;
    }

    @Override
    public void register(String userName, String password) {
        // 加密
        String md5String = Md5Util.getMD5String(password);

        // 添加
        userMapper.add(userName, md5String);
    }
}
