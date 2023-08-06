package com.yzc.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yzc.entities.User;
import com.yzc.mapper.UserMapper;
import com.yzc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yangzicheng
 * @Date Created in 16:39 2022/3/22
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String userPwd) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        String encodePwd = DigestUtil.md5Hex(userPwd);
        qw.eq("user_name",userName).eq("user_pwd",encodePwd);
        User user = userMapper.selectOne(qw);
        return user;
    }

    @Override
    public int updatePwd(Integer uid, String userPwd) {
        UpdateWrapper<User> qw = new UpdateWrapper<>();
        String encodePwd = DigestUtil.md5Hex(userPwd);
        qw.eq("user_id", uid).set("user_pwd", encodePwd);
        int update = userMapper.update(null, qw);
        return update;
    }

    @Override
    public int insertUser(User user) {
        String userPwd = user.getUserPwd();
        String s = DigestUtil.md5Hex(userPwd);
        user.setUserPwd(s);
        int insert = userMapper.insert(user);
        return insert;
    }


}
