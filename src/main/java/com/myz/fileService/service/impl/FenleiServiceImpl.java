package com.myz.fileService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myz.fileService.entity.Fenlei;
import com.myz.fileService.entity.FenleiTreeVo;
import com.myz.fileService.mapper.FenleiMapper;
import com.myz.fileService.service.FenleiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@Service
public class FenleiServiceImpl extends ServiceImpl<FenleiMapper, Fenlei> implements FenleiService {

    @Autowired
    private FenleiMapper fenleiMapper;

    //根据分类id查询所有子列表
    //仅查询下一级目录，返回下一级每个子节点的数据(除children字段)
    @Override
    public List<FenleiTreeVo> getChildrenFenleiList(Integer id) {

        QueryWrapper<Fenlei> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", id);

        List<FenleiTreeVo> childrenList = fenleiMapper.selectList(queryWrapper)
                .stream()
                .map(e -> {
                    return e.buildFenleiTreeVo();
                })
                .collect(Collectors.toList());
        return childrenList;
    }


    //根据一个节点，递归填充所有子节点(包括子节点的children字段)
    //递归所有的子列表，查询出目录树
    public FenleiTreeVo getFenleiTree(FenleiTreeVo fenleiTreeVo){
        //先查出子Fenlei列表
        List<FenleiTreeVo> childrenFenleiList = getChildrenFenleiList(fenleiTreeVo.getValue());
        fenleiTreeVo.setChildren(childrenFenleiList);
        //如果子列表还有子节点
        if(childrenFenleiList!=null && childrenFenleiList.size()>0){
            for(FenleiTreeVo f : childrenFenleiList){
                getFenleiTree(f);
            }
        }
        return fenleiTreeVo;
    }

    //传一个最高分类id，获得分类Tree列表
    @Override
    public List<FenleiTreeVo> getFenLeiTreeVos(int rootFenleiId) {
        FenleiTreeVo root = new FenleiTreeVo();
        root.setValue(rootFenleiId);

        FenleiTreeVo fenleiTree = getFenleiTree(root);

        return fenleiTree.getChildren();
    }




}
