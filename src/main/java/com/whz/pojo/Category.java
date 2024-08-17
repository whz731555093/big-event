package com.whz.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    // 限定该字段不能为空
    @NotNull(groups = Update.class)
    private Integer id;//主键ID

    // 限定该字段不能为空，且不能为空字符串
    @NotEmpty
    private String categoryName;//分类名称

    @NotEmpty
    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    // 指定获取到的JSON的日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    // 如果某个校验项没设定分组，默认属于Default分组
    // 分组之间可以继承，A extends B，则A中拥有B中的所有校验项

    public interface Add extends Default {
    }

    public interface Update extends Default {
    }
}
