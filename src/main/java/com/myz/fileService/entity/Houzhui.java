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
@TableName("tab_houzhui")
@ApiModel(value="Houzhui对象", description="")
public class Houzhui implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件后缀Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "后缀名字")
    private String houzhui;

    @ApiModelProperty(value = "图片路径")
    @TableField("imagePath")
    private String imagePath;


}
