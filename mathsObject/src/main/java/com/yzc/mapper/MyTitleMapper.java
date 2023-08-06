package com.yzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.MyTitle;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author yangzicheng
 * @Date Created in 16:34 2022/3/22
 */
@Repository
public interface MyTitleMapper extends BaseMapper<MyTitle> {
    List<MyTitle> queryAllWrongTitle(Integer uid);
    PageInfo<MyTitle> queryAllWrongTitle(Integer uid, Integer pageNum, Integer pageSize);
    List<MyTitle> myTitle(Integer uid);

    int saveMyTitle(MyTitle myTitle);

    int updateMyTitle(Map map);

    List<MyTitle> myTitleByClassify(Map map);

    int completeTitle(Map map);
}
