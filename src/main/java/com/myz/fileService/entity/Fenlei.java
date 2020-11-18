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
@TableName("tab_fenlei")
@ApiModel(value="Fenlei对象", description="")
public class Fenlei implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    @TableField("fenleiName")
    private String fenleiName;

    @ApiModelProperty(value = "分类等级")
    private Integer level;

    @ApiModelProperty(value = "父级分类Id")
    @TableField("parentId")
    private Integer parentId;

    @ApiModelProperty(value = "url访问链接(该链接可以访问该文件分类下的文件信息)")
    private String url;

   public FenleiTreeVo buildFenleiTreeVo(){
       FenleiTreeVo fenleiTreeVo = new FenleiTreeVo();
       fenleiTreeVo.setLabel(this.fenleiName);
       fenleiTreeVo.setValue(this.id);
       return fenleiTreeVo;
   }

}
