package com.yzc.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mytitle")
public class MyTitle {
    private Integer mid;                  //    编号
    private User user;               //    用户id
    private Title title;              //    题目id
    private Integer state;                //    题目状态，0表示未做，1表示正确，2表示错误
}
