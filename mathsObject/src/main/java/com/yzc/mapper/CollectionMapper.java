package com.yzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author yangzicheng
 * @Date Created in 16:34 2022/3/22
 */
@Repository
public interface CollectionMapper extends BaseMapper<CollectionTitle> {
    List<CollectionTitle> queryAllCollectTitle(Integer uid);
    PageInfo<CollectionTitle> queryAllCollectTitle(Integer uid, Integer pageNum, Integer pageSize);

    int saveTitle(CollectionTitle collectionTitle);

    int deleteTitle(Integer cid);



}
