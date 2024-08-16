package com.whz.service.impl;

import com.whz.controller.UserController;
import com.whz.mapper.UserMapper;
import com.whz.pojo.User;
import com.whz.service.UserService;
import com.whz.utils.Md5Util;
import com.whz.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author whz
 * @description
 * @since 2024/8/11 22:23
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        logger.info("您的用户名为: {}", username);
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        // 加密
        String md5String = Md5Util.getMD5String(password);

        // 添加
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        // 获取更新时间
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPassword) {
        Map<String, Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPassword), id);
    }
}
