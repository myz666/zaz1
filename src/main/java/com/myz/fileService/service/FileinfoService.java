package com.myz.fileService.service;

import com.myz.fileService.entity.FileDto;
import com.myz.fileService.entity.Fileinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myz.fileService.mapper.FileinfoMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
public interface FileinfoService extends IService<Fileinfo> {



//    @Select("select id,fileName,houzhuiId,fenleiId,filePath,miaoshu from tab_fileinfo")
//    List<Fileinfo> getAllFile(Integer fenLeiId);
/**
    List<FileDto>  getFileDtoByFenleiId(int FenleiId);

    List<FileDto> getFileDtoByLikeFileName(String fileName);
*/
    Map<String,Object> getFileDtoByFenLeiIdWithPage(int FenleiId, int currentPage, int pageSize);

    Map<String,Object> getFileDtoByLikeNameWithPage(String LikeName,int currentPage,int pageSize);


    FileDto insertFile(FileDto fileDto);

}
