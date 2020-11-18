package com.myz.fileService.service;

import com.myz.fileService.entity.Fenlei;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myz.fileService.entity.FenleiTreeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
public interface FenleiService extends IService<Fenlei> {

    List<FenleiTreeVo> getChildrenFenleiList(Integer id);

    List<FenleiTreeVo> getFenLeiTreeVos(int rootFenleiId);
}
