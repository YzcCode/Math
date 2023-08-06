package com.yzc.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private Integer userId;             //用户id
    private String userName;            //用户名
    private String userPwd;             //密码
    private String userTel;             //电话

}
