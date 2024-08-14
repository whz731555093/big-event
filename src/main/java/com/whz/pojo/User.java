package com.whz.pojo;


import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名

    @JsonIgnore // 让spring MVC把当前对象转换成json字符串时，忽略注解的变量
    private String password;//密码

    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
