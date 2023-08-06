package com.yzc.controller;

import com.yzc.common.CommonResult;
import com.yzc.common.ResponseCode;
import com.yzc.entities.Title;
import com.yzc.service.TitleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:41 2022/3/22
 */
@RestController
@Slf4j
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private TitleService titleService;


    @ApiOperation("将所有题目返回给前端页面")
    @GetMapping("/listTitle")
    public CommonResult<List<Title>> listTitle(){
        List<Title> titles = titleService.queryAllTitle();
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",titles);

    }
    @ApiOperation("根据类别将题目返回给前端页面")
    @GetMapping("/listTitleByCategory/{category}")
    public CommonResult<List<Title>> listTitleByCategory(
            @PathVariable("category") Integer category
    ){
        List<Title> titles = titleService.queryAllTitleByCategory(category);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",titles);

    }
    @ApiOperation("根据题型将题目返回给前端页面")
    @GetMapping("/listTitleByType/{type}")
    public CommonResult<List<Title>> listTitleByType(
            @PathVariable("type") Integer type
    ){
        List<Title> titles = titleService.queryAllTitleByType(type);
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",titles);

    }

    @ApiOperation("添加题目")
    @PostMapping("/saveTitle")
    public CommonResult saveTitle(
            @ApiParam(name="Title" , value="添加的题目信息",required = true)
            @RequestBody Title title) {
        int rows = titleService.insertTitle(title);
        // 条件成立：表示添加成功，否则添加失败
        if (rows > 0 ) {
            return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",rows);
        } else {
            return new CommonResult<>(ResponseCode.RESPONSE_FAIL,"fail",rows);
        }
    }

    @ApiOperation("根据题目ID删除题目")
    @GetMapping("/deleteTitle/{tid}")
    public CommonResult deleteTitleById(
            @ApiParam(name="tid",value="题目ID",required = true)
            @PathVariable("tid") Integer tid) {
        // 执行删除操作受影响行数
        int rows = titleService.deleteTitle(tid);
        // 条件成立：删除成功
        if (rows > 0) {
            return new CommonResult(ResponseCode.RESPONSE_SUCCESS,"success",rows);
        } else {
            return new CommonResult(ResponseCode.RESPONSE_FAIL,"fail",rows);
        }
    }

    @ApiOperation("修改题目信息")
    @PostMapping("/updateTitle")
    public CommonResult updateTitle(
            @ApiParam(name="title" , value="要修改的题目信息", required = true)
            @RequestBody Title title) {
        // 执行修改返回的受影响行数
        int rows = titleService.updataTitle(title);
        // 条件成立：修改成功
        if (rows > 0) {
            return new CommonResult(ResponseCode.RESPONSE_SUCCESS,"success");
        } else {
            return new CommonResult(ResponseCode.RESPONSE_FAIL,"fail");
        }
    }

    @ApiOperation("根据题目ID获取题目信息")
    @GetMapping("/preUpdateTitle/{tid}")
    public CommonResult<Title> preUpdateTitle(
            @ApiParam(name="tid",value="题目ID",required = true)
            @PathVariable("tid") Integer tid
    ) {
        Title title = titleService.getTitleById(tid);
        if(null == title){
            return new CommonResult<>(ResponseCode.RESPONSE_FAIL,"fail");
        }else {
            return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS,"success",title);
        }
    }

}
