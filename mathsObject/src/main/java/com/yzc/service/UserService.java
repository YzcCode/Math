package com.yzc.service;

import com.yzc.entities.User;

/**
 * @Author yangzicheng
 * @Date Created in 16:38 2022/3/22
 */
public interface UserService {
    User login(String userName, String userPwd);
    int updatePwd(Integer uid, String userPwd);
    int insertUser(User user);

}
