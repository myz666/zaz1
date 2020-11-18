package com.myz.fileService.controller;


import com.myz.fileService.Result.R;
import com.myz.fileService.entity.FenleiTreeVo;
import com.myz.fileService.service.FenleiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/fenlei")
@CrossOrigin
public class FenleiController {

    @Autowired
    FenleiService fenleiService;

    @GetMapping("/fenLeiTree")
    public R getAllFenLeiList(){
        List<FenleiTreeVo> fenLeiTreeVos = fenleiService.getFenLeiTreeVos(0);
        return R.ok().data("data",fenLeiTreeVos);
    }

}

