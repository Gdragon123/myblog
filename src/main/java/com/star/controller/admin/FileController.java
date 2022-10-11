package com.star.controller.admin;

import com.star.common.R;
import com.star.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 文件上传
 */
@Api("文件上传")
@Controller
@RequestMapping("/oss/file")
@CrossOrigin    //解决跨域问题
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public R upload(MultipartFile file) {
        if (Objects.isNull(file)) {
            return R.error().message("请上传文件");
        }
        //返回文件的访问路径
        String url = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url", url);
    }

    @ApiOperation("文件上传")
    @GetMapping("/upload-callback")
    public String uploadCallback() {
        return "admin/upload-callback";
    }

}
