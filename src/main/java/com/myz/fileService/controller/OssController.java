package com.myz.fileService.controller;

import com.myz.fileService.Result.R;
import com.myz.fileService.entity.FileDto;
import com.myz.fileService.service.OssService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("uploadFile")
    public R uploadFile(MultipartFile file){
        String url= ossService.uploadAvatar(file);
        return R.ok().data("url",url);

    }

    @PostMapping("deleteFile/{dirName}/{fileName}")
    public R deleteFile(@ApiParam(value = "文件路径", required = true) @PathVariable String dirName,
                        @ApiParam(value = "文件路径", required = true) @PathVariable String fileName){
        try {
            ossService.deleteFile(dirName,fileName);
            return R.ok();
        }catch (Exception e){
            R.error().data("error",e.getMessage());
        }
        return R.error();
    }

}
