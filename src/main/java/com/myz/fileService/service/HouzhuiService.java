package com.myz.fileService.service;

import com.myz.fileService.entity.Houzhui;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
public interface HouzhuiService extends IService<Houzhui> {

   Houzhui getHouzhuiByid(Integer id);
   Houzhui getHouZhuiByhouZhuiName(String houzhui);

}
