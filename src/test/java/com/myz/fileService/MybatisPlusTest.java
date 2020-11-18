package com.myz.fileService;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myz.fileService.entity.*;
import com.myz.fileService.mapper.FileinfoMapper;
import com.myz.fileService.mapper.HouzhuiMapper;
import com.myz.fileService.service.FenleiService;
import com.myz.fileService.service.FileinfoService;
import com.myz.fileService.service.HouzhuiService;
import com.myz.fileService.service.OssService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private HouzhuiMapper houzhuiMapper;

    @Autowired
    private FenleiService fenleiService;

    @Autowired
    private FileinfoService fileinfoService;

    @Autowired
    private FileinfoMapper fileinfoMapper;

    @Autowired
    private HouzhuiService houzhuiService;

    @Autowired
    OssService ossService;

    @Autowired
    RedisTemplate redisTemplate;

   @Test
    public void testFindAll(){
        List<Houzhui> houzhuis = houzhuiMapper.selectList(null);
        System.out.println(houzhuis);
    }

    @Test
    public void testFindChildrenFenlei(){
//        List<FenleiTreeDto> childrenFenleiList = fenleiService.getChildrenFenleiList(2);
//        System.out.println(childrenFenleiList);

    }



    @Test
    public void testGetFilesDtoByFenLeiId(){
        System.out.println(fileinfoMapper.getFilesDtoByFenLeiId(4));
    }

    @Test
    public void test1(){
        System.out.println(fileinfoMapper.getFilesDtoByLikeFileName("线程"));
    }

    @Test
    public void test2(){
//       fileinfoService.getFileDtoByFenleiId(3);
    }

    @Test
    public void test3(){
        Houzhui houzhui = houzhuiService.getHouzhuiByid(2);
        System.out.println(houzhui);
    }

    @Test
    public void test4(){
        System.out.println(redisTemplate.boundValueOps("houzhui_1").get());
    }

    @Test
    public void test5(){
//        System.out.println(fileinfoService.getFileDtoByFenleiId(3));
    }

    @Test
    public void test6(){
        BoundValueOperations ops = redisTemplate.boundValueOps("houzhui_2");
        Object o = ops.get();
        Houzhui houzhui = JSON.parseObject(o.toString(), Houzhui.class);
        System.out.println(houzhui);
    }

    @Test
    public  void test7(){
//       fileinfoService.getFileDtoByFenLeiIdWithPage(3,1,3).forEach(System.out::println);
    }



    @Test
    public void test9(){
        List<FenleiTreeVo> fenLeiTreeVos = fenleiService.getFenLeiTreeVos(0);
        for(FenleiTreeVo f :fenLeiTreeVos){
            System.out.println(f);
        }
    }

    @Test
    public void test10(){

        Page<Fileinfo> page = new Page<>(0,2);
        fileinfoMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);

    }

    @Test
    public void test11(){
        System.out.println(houzhuiService.getHouZhuiByhouZhuiName("doc"));
    }

    @Test
    public void test12(){
//       ossService.deleteFile("https://myz-files.oss-cn-shenzhen.aliyuncs.com/2020-11-04/2dd8f0db98ac4ca7be99e29fd6d15c08线程池原理.doc");
    }
}
