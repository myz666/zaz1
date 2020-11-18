package com.myz.fileService.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FileDto {

    @ApiModelProperty(value = "标识一个唯一的文件信息")
    private Integer id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "后缀名称")
    private String houzhui;

    @ApiModelProperty(value = "后缀图片")
    private String imagePath;

    @ApiModelProperty(value = "分类Id")
    private Integer fenleiId;

    @ApiModelProperty(value = "文件存储路径")
    private String filePath;

    @ApiModelProperty(value = "文件描述信息")
    private String miaoshu;

    public String getFileName() {
        return fileName;
    }

    public Fileinfo buildFileInfo(){
        Fileinfo fileinfo = new Fileinfo();
        fileinfo.setFenleiId(this.fenleiId);
        fileinfo.setFileName(this.fileName);
        fileinfo.setFilePath(this.filePath);
        return fileinfo;
    }

}
