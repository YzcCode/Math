package com.yzc.entities;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("collection")
public class CollectionTitle {
    private Integer collectionId;             //收藏id
    private User user;                   //用户id
    private Title title;                  //题目编号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;                        //收藏日期
}
