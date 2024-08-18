package com.whz.controller;

import com.whz.pojo.Result;
import com.whz.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 15:57
 */
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 把文件内容存储到本地磁盘
        String originalFileName = file.getOriginalFilename();

        // 保证文件名唯一性，防止同名覆盖
        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

        // 服务器存储文件的路径
        file.transferTo(new File("D:\\Project\\big-event\\testStoreFiles\\" + fileName));

        // 修改为服务器存储的url地址
        // String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        // return Result.success(url);

        return Result.success("远端服务器url访问地址：...");
    }
}
