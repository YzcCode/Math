package com.yzc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.Title;
import com.yzc.entities.User;
import com.yzc.mapper.CollectionMapper;
import com.yzc.service.CollectionService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:40 2022/3/22
 */
@Service
@Slf4j
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;


    @Override
    public List<CollectionTitle> queryAllCollectTitle(Integer uid) {
        return collectionMapper.queryAllCollectTitle(uid);
    }

    @Override
    public PageInfo<CollectionTitle> queryAllCollectTitle(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CollectionTitle> collectionTitles = collectionMapper.queryAllCollectTitle(uid);
        return new PageInfo<>(collectionTitles);
    }

    @Override
    public int saveTitle(CollectionTitle collectionTitle) {
        int i = collectionMapper.saveTitle(collectionTitle);
        return i;
    }

    @Override
    public int deleteTitle(Integer cid) {
        return collectionMapper.deleteTitle(cid);
    }
}
