package com.yzc.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yzc.common.CommonResult;
import com.yzc.common.ResponseCode;
import com.yzc.entities.User;
import com.yzc.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author yangzicheng
 * @Date Created in 16:41 2022/3/22
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("登录")
    @PostMapping(value="/login")
    public CommonResult loginUser(
            @ApiParam(name="user",value="表单采集的用户登陆信息",required = true)
            HttpSession session,
            @RequestBody User user) {
        User user1 = userService.login(user.getUserName(), user.getUserPwd());
        if (user1 != null) {
            session.setAttribute("user",user1);
            return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success",user1);

        } else {
            return new CommonResult<>(ResponseCode.RESPONSE_FAIL, "fail","用户名或密码错误");
        }

    }

    @ApiOperation("注册")
    @PostMapping(value="/register")
    public CommonResult registerUser(String userPwd1 ,  String userPwd2, User user) {
        if(StrUtil.equals(userPwd1,userPwd2)){
            User user1 = userService.login(user.getUserName(), userPwd1);
            if(user1 != null){
                return new CommonResult<>(ResponseCode.RESPONSE_FAIL, "fail","该用户已存在");

            }else{
                user.setUserPwd(userPwd1);
                int i = userService.insertUser(user);
                return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success",i);
            }
        }else{
            return new CommonResult<>(ResponseCode.RESPONSE_FAIL, "fail","两次输入的密码不一致!请确认后重新输入");
        }

    }
    @ApiOperation("退出")
    @GetMapping("/exit")
    public CommonResult exitUser(HttpSession session){
        session.invalidate();
        return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success","退出成功");
    }
    @ApiOperation("修改密码")
    @PostMapping(value = "/changePwd")
    public CommonResult changePwd(String oldPwd, String newPwd1,String newPwd2, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            String encodePwd = DigestUtil.md5Hex(oldPwd);
            if(StrUtil.equals(encodePwd,user.getUserPwd())){
                log.info(oldPwd);
                if(StrUtil.equals(newPwd1,newPwd2)){
                    log.info(newPwd1);
                    userService.updatePwd(user.getUserId(),newPwd1);
                    return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "success","更改密码成功！请重新登录");
                }else{
                    return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "fail","两次输入的新密码不一致!");

                }
            }else{
                return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "fail","原密码错误!");
            }
        }else{
            return new CommonResult<>(ResponseCode.RESPONSE_SUCCESS, "fail","请先登录!");
        }

    }

}
