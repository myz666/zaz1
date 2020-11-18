package com.myz.fileService.controller;


import com.myz.fileService.Result.R;
import com.myz.fileService.entity.Houzhui;
import com.myz.fileService.service.HouzhuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/fileService/houzhui")
public class HouzhuiController {

    @Autowired
    private HouzhuiService houzhuiService;

    @RequestMapping("findAll")
    public R findAll(){
        List<Houzhui> list = houzhuiService.list(null);
        return R.ok().data("total",list.size()).data("rows",list);
    }

}

