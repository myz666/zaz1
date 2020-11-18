package com.myz.fileService.mapper;

import com.myz.fileService.entity.FileDto;
import com.myz.fileService.entity.Fileinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@Mapper
public interface FileinfoMapper extends BaseMapper<Fileinfo> {

//    Fileinfo getByMyzId(@Param("id") Integer id);
    List<FileDto> getFilesDtoByFenLeiId(@Param("fenleiId") int fenleiId);

    List<FileDto> getFilesDtoByLikeFileName(@Param("fileName") String fileName);

}
