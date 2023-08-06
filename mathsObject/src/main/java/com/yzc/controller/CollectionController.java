package com.yzc.controller;

import com.github.pagehelper.PageInfo;
import com.yzc.common.CommonResult;
import com.yzc.common.ResponseCode;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.Title;
import com.yzc.entities.User;
import com.yzc.service.CollectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:41 2022/3/22
 */
@RestController
@RequestMapping("/collect")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @ApiOperation("获取所有的收藏的题")
    @GetMapping("/queryAllCollectTitle/{uid}")
    public CommonResult<List<CollectionTitle>> queryAllCollectTitle(
            @PathVariable("uid") Integer uid
    ){
        List<CollectionTitle> collectionTitles = collectionService.queryAllCollectTitle(uid);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",collectionTitles);
    }
    @ApiOperation("获取所有收藏的题(分页)")
    @GetMapping("/queryAllCollectTitlePage/{uid}/{pageNum}/{pageSize}")
    public CommonResult<PageInfo> queryAllCollectTitlePage(
            @PathVariable("uid") Integer uid,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize
    ){
        PageInfo<CollectionTitle> collectionTitles = collectionService.queryAllCollectTitle(uid,pageNum,pageSize);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",collectionTitles);
    }

    @ApiOperation("错题、正确的题收藏需要插入数据")
    @PostMapping("/saveTitle")
    public CommonResult saveTitle(Integer uid, Integer tid, CollectionTitle collectionTitle){
        User user = new User();
        user.setUserId(uid);
        Title title = new Title();
        title.setTitleId(tid);
        collectionTitle.setUser(user);
        collectionTitle.setTitle(title);
        int i = collectionService.saveTitle(collectionTitle);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success", i);
    }

    @ApiOperation("取消收藏、全部删除")
    @GetMapping("/deleteTitle/{cid}")
    public CommonResult deleteTitle(@PathVariable("cid") Integer cid){
        int i = collectionService.deleteTitle(cid);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success", i);
    }
}
