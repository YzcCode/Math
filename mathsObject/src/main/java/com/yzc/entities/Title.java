package com.yzc.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("title")
public class Title{
    private Integer titleId;           //题目编号
    private Integer titleCategory;     //科目类别 0表示高数，1表示线代，2表示概率论
    private Integer titleType;         //题目类型 0表示选择，1表示填空，2表示简答
    private String titleDescribes;     //题目描述
    private String titleImage;         //题目图片
    private String titleAnswer;        //答案
    private String titleAnalysis;      //解析

}
