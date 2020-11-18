package com.myz.fileService.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tab_fileinfo")
@ApiModel(value="Fileinfo对象", description="")
public class Fileinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识一个唯一的文件信息")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名称")
    @TableField("fileName")
    private String fileName;

    @ApiModelProperty(value = "后缀名称ID")
    @TableField("houzhuiId")
    private Integer houzhuiId;

    @ApiModelProperty(value = "分类Id,表示该条文件信息属于哪一种分类")
    @TableField("fenleiId")
    private Integer fenleiId;

    @ApiModelProperty(value = "文件存储路径")
    @TableField("filePath")
    private String filePath;

    @ApiModelProperty(value = "对文件的描述信息")
    private String miaoshu;

    public FileDto buildFileDto(){
        FileDto fileDto = new FileDto();
        fileDto.setId(this.id);
        fileDto.setFenleiId(this.fenleiId);
        fileDto.setFileName(this.fileName);
        fileDto.setFilePath(this.filePath);
        fileDto.setMiaoshu(this.miaoshu);
        return fileDto;
    }

}
