package com.myz.fileService.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myz.fileService.entity.Houzhui;
import com.myz.fileService.mapper.HouzhuiMapper;
import com.myz.fileService.service.HouzhuiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author myz
 * @since 2020-10-29
 */
@Service
public class HouzhuiServiceImpl extends ServiceImpl<HouzhuiMapper, Houzhui> implements HouzhuiService {

    @Autowired
    private HouzhuiMapper houzhuiMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //根据后缀id获取后缀名
    @Override
    public Houzhui getHouzhuiByid(Integer id) {
        String redisKey = "houzhui_"+id;
        Houzhui houzhui = null;
        BoundValueOperations ops = redisTemplate.boundValueOps(redisKey);

        //如果redis里有则去redis里取
        if(redisTemplate.hasKey(redisKey)){
            houzhui = JSON.parseObject(ops.get().toString(), Houzhui.class);
        }else{
            //没有则去数据库取，并把它存到redis
            houzhui = houzhuiMapper.selectById(id);
            if(houzhui!=null) ops.set(JSON.toJSONString(houzhui));
        }
        //都未找到该后缀，使用默认后缀
        if(houzhui==null){
            houzhui = getHouzhuiByid(0);
        }
        return houzhui;
    }

    @Override
    public Houzhui getHouZhuiByhouZhuiName(String houzhui) {
        String redisKey = "houzhuiStr_"+houzhui;
        Houzhui hz = null;
        BoundValueOperations ops = redisTemplate.boundValueOps(redisKey);
        if(redisTemplate.hasKey(redisKey)){
            hz = JSON.parseObject(ops.get().toString(), Houzhui.class);
        }else{
            //没有则去数据库取，并把它存到redis
            QueryWrapper<Houzhui> queryWrapper = new QueryWrapper();
            queryWrapper.eq("houzhui",houzhui);
            hz = houzhuiMapper.selectOne(queryWrapper);
            if(hz!=null) ops.set(JSON.toJSONString(hz));
        }
        //都未找到该后缀，使用默认后缀
        if(hz==null){
            Houzhui newHouZhui = new Houzhui();
            newHouZhui.setHouzhui(houzhui);
            newHouZhui.setImagePath(getHouZhuiByhouZhuiName("default").getImagePath());
            int changeLines = houzhuiMapper.insert(newHouZhui);
            if(changeLines>0) return newHouZhui;
            else getHouZhuiByhouZhuiName(houzhui);
        }
        return hz;
    }
}
