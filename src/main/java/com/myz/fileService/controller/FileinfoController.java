package com.myz.fileService.controller;


import com.myz.fileService.Result.R;
import com.myz.fileService.entity.FileDto;
import com.myz.fileService.entity.Fileinfo;
import com.myz.fileService.mapper.FileinfoMapper;
import com.myz.fileService.service.FileinfoService;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/File")
@CrossOrigin
public class FileinfoController {


    @Autowired
    FileinfoService fileinfoService;

    @Autowired
    FileinfoMapper fileinfoMapper;

    private static Logger logger = LoggerFactory.getLogger(FileinfoController.class);

    @PostMapping("/update")
    public R updateFile(@ApiParam(value = "文件信息", required = true) @RequestBody Fileinfo fileinfo){

        logger.info("输出hhghhhhhhhh");

        int changeLines = fileinfoMapper.updateById(fileinfo);
        if(changeLines>0){
            return R.ok().data("data",fileinfo);
        }else {
            return R.error();
        }
    }

    @PostMapping("/insert")
    public R insertFile(@ApiParam(value = "文件信息", required = true) @RequestBody FileDto fileDto){
        FileDto insertFileDto = fileinfoService.insertFile(fileDto);
        if(insertFileDto!=null){
            return R.ok().data("data",insertFileDto);
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/delete/{id}")
    public R deleteFile(@ApiParam(value = "文件Id", required = true) @PathVariable int id){
        int changeLines = fileinfoMapper.deleteById(id);
        if(changeLines>0){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("getFileDtoByFenleiId/{fenLeiId}/{currentPage}/{pageSize}")
    public R getFileDtoByFenleiId(
            @ApiParam(value = "分类Id", required = true) @PathVariable int fenLeiId,
            @ApiParam(value = "当前页", required = true) @PathVariable int currentPage,
            @ApiParam(value = "每页显示条数", required = true) @PathVariable int pageSize
    ){
        Map<String, Object> answerMap = fileinfoService.getFileDtoByFenLeiIdWithPage(fenLeiId, currentPage, pageSize);
        return R.ok().data("total",answerMap.get("total")).data("data",answerMap.get("files"));
    }

    @GetMapping("getFileDtoByLikeName/{fileName}/{currentPage}/{pageSize}")
    public R getFileDtoByLikeName(
            @ApiParam(value = "文件名", required = true) @PathVariable String fileName,
            @ApiParam(value = "当前页", required = true) @PathVariable int currentPage,
            @ApiParam(value = "每页显示条数", required = true) @PathVariable int pageSize
    ){
        Map<String, Object> answerMap = fileinfoService.getFileDtoByLikeNameWithPage(fileName, currentPage, pageSize);
        return R.ok().data("total",answerMap.get("total")).data("data",answerMap.get("files"));
    }
}

