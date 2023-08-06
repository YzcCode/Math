package com.yzc.controller;

import com.github.pagehelper.PageInfo;
import com.yzc.common.CommonResult;
import com.yzc.common.ResponseCode;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.MyTitle;
import com.yzc.entities.Title;
import com.yzc.entities.User;
import com.yzc.service.MyTitleService;
import com.yzc.service.TitleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:41 2022/3/22
 */
@RestController
@RequestMapping("/my")
public class MyTitleController {
    @Autowired
    private MyTitleService myTitleService;
    @Autowired
    private TitleService titleService;
    @ApiOperation("返回用户的所有题目")
    @GetMapping("/allMyTitle/{uid}")
    public CommonResult<List<MyTitle>> allMyTitle(
            @PathVariable("uid") Integer uid
    ){
        List<MyTitle> myTitles = myTitleService.queryMyTitleByUserId(uid);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success", myTitles);
    }

    @ApiOperation("更新用户的题目状态，未做、正确、错误。错题重新做做对可以更改state为1")
    @PostMapping(value = "/updateState")
    public CommonResult updateState(Integer mid ,Integer state){
        int i = myTitleService.updataMyTitleState(mid, state);
        return new CommonResult(ResponseCode.RESPONSE_SUCCESS,"success",i);
    }

    @ApiOperation("当用户第一次登录时(注册成功时)给用户添加所有题目")
    @PostMapping("/saveMyTitle")
    public CommonResult saveMyTitle(Integer uid,MyTitle myTitle){
        User user = new User();
        user.setUserId(uid);
        List<Title> titles = titleService.queryAllTitle();
        int i = 0;
        for (Title title : titles) {
            myTitle.setTitle(title);
            myTitle.setUser(user);
            i = myTitleService.saveMyTitle(myTitle);
        }
        if(i > 0){
            return new CommonResult(ResponseCode.RESPONSE_SUCCESS,"success",i);
        }else{
            return new CommonResult(ResponseCode.RESPONSE_FAIL,"fail",i);
        }

    }
    @ApiOperation("根据题目的属性与题型分类(分页)")
    @GetMapping("/myTitleByClassifyPage/{uid}/{category}/{type}/{pageNum}/{pageSize}")
    public CommonResult<PageInfo> myTitleByClassifyPage(
            @PathVariable("uid") Integer uid,
            @PathVariable("category") Integer category,
            @PathVariable("type") Integer type,
            @ApiParam(name="pageNum",value="当前页",required = true)
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize
    ){
        PageInfo<MyTitle> myTitles = myTitleService.myTitleByClassify(uid, category, type,pageNum, pageSize);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success", myTitles);
    }
    @ApiOperation("根据题目的属性与题型分类")
    @GetMapping("/myTitleByClassify/{uid}/{category}/{type}")
    public CommonResult<List<MyTitle>> myTitleByClassify(
            @PathVariable("uid") Integer uid,
            @PathVariable("category") Integer category,
            @PathVariable("type") Integer type
    ){
        List<MyTitle> myTitles = myTitleService.myTitleByClassify(uid, category, type);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success", myTitles);
    }


    @ApiOperation("返回已完成的题目数目")
    @GetMapping("/completeTitle/{uid}/{category}/{type}")
    public CommonResult completeTitle(
            @PathVariable("uid") Integer uid,
            @PathVariable("category") Integer category,
            @PathVariable("type") Integer type
    ){
        int i = myTitleService.completeTitle(uid, category,type);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success", i);
    }


    @ApiOperation("获取所有的错题")
    @GetMapping("/queryAllWrongTitle/{uid}")
    public CommonResult<List<MyTitle>> queryAllWrongTitle(
            @PathVariable("uid") Integer uid
    ){
        List<MyTitle> collectionTitles = myTitleService.queryAllWrongTitle(uid);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",collectionTitles);
    }
    @ApiOperation("获取所有的错题(分页)")
    @GetMapping("/queryAllWrongTitlePage/{uid}/{pageNum}/{pageSize}")
    public CommonResult<PageInfo> queryAllWrongTitlePage(
            @PathVariable("uid") Integer uid,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize
    ){
        PageInfo<MyTitle> collectionTitles = myTitleService.queryAllWrongTitle(uid,pageNum,pageSize);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",collectionTitles);
    }
}
