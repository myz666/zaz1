package com.myz.fileService.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myz.fileService.entity.FileDto;
import com.myz.fileService.entity.Fileinfo;
import com.myz.fileService.entity.Houzhui;
import com.myz.fileService.mapper.FileinfoMapper;
import com.myz.fileService.service.FileinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myz.fileService.service.HouzhuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@Service
public class FileinfoServiceImpl extends ServiceImpl<FileinfoMapper, Fileinfo> implements FileinfoService {

    @Autowired
    private FileinfoMapper fileinfoMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    HouzhuiService houzhuiService;

/**
    //根据分类ID查询文件
    @Override
    public List<FileDto> getFileDtoByFenleiId(int fenLeiId) {
        if(fenLeiId<0){
            return null;
        }
        List<FileDto> files=null;
        try{
            QueryWrapper<Fileinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fenleiId", fenLeiId);

            files = fileinfoMapper.selectList(queryWrapper).stream()
                    .map(e -> {
                        Houzhui houzhui = houzhuiService.getHouzhuiByid(e.getHouzhuiId());
                        FileDto fileDto = e.buildFileDto();
                        fileDto.setHouzhui(houzhui.getHouzhui());
                        fileDto.setImagePath(houzhui.getImagePath());
                        return fileDto;
                    })
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return files;
    }

    //根据文件名模糊查询
    @Override
    public List<FileDto> getFileDtoByLikeFileName(String fileName) {
        if("".equals(fileName) || fileName==null){
            return null;
        }
        List<FileDto> files=null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("fileName",fileName);
        List<Fileinfo> list = fileinfoMapper.selectList(queryWrapper);
        try {
            files = list.stream().map(e -> {
                Houzhui houzhui = houzhuiService.getHouzhuiByid(e.getHouzhuiId());
                FileDto fileDto = e.buildFileDto();
                fileDto.setHouzhui(houzhui.getHouzhui());
                return fileDto;
            }).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return files;
    }
**/
    public Map<String,Object> getFileDtoByPage(Page page, QueryWrapper queryWrapper){
        Map<String,Object> answerMap = new HashMap<>();
        List<FileDto> files=null;
        IPage pages = fileinfoMapper.selectPage(page, queryWrapper);
        List<Fileinfo> list = pages.getRecords();
        long total = pages.getTotal();
        answerMap.put("total",total);
        try {
            files = list.stream().map(e -> {
                Houzhui houzhui = houzhuiService.getHouzhuiByid(e.getHouzhuiId());
                FileDto fileDto = e.buildFileDto();
                fileDto.setHouzhui(houzhui.getHouzhui());
                fileDto.setImagePath(houzhui.getImagePath());
                return fileDto;
            }).collect(Collectors.toList());
            answerMap.put("files",files);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return answerMap;
    }

    @Override
    public Map<String,Object> getFileDtoByFenLeiIdWithPage(int FenleiId, int currentPage, int pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fenleiId", FenleiId);
        queryWrapper.orderByDesc("id");
        Page page = new Page(currentPage,pageSize);
        Map<String, Object> answerMap = getFileDtoByPage(page, queryWrapper);
        return answerMap;
    }

    @Override
    public Map<String,Object> getFileDtoByLikeNameWithPage(String LikeName, int currentPage, int pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("fileName",LikeName);
        queryWrapper.orderByDesc("id");
        Page page = new Page(currentPage,pageSize);
        Map<String, Object> answerMap = getFileDtoByPage(page, queryWrapper);
        return answerMap;
    }

    @Override
    public FileDto insertFile(FileDto fileDto) {
        Fileinfo fileinfo = fileDto.buildFileInfo();
        int houzhuiId = houzhuiService.getHouZhuiByhouZhuiName(fileDto.getHouzhui()).getId();
        fileinfo.setHouzhuiId(houzhuiId);
        int changeLines = fileinfoMapper.insert(fileinfo);
        if(changeLines>0){
            return fileinfo.buildFileDto();
        }
        return null;
    }


}
