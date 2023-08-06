package com.yzc.service;

import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:38 2022/3/22
 */
public interface CollectionService {

    List<CollectionTitle> queryAllCollectTitle(Integer uid);
    PageInfo<CollectionTitle> queryAllCollectTitle(Integer uid, Integer pageNum, Integer pageSize);

    int saveTitle(CollectionTitle collectionTitle);

    int deleteTitle(Integer cid);
}
