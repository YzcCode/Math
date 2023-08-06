package com.yzc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.MyTitle;
import com.yzc.mapper.MyTitleMapper;
import com.yzc.service.MyTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yzc
 * @Date Created in 16:39 2022/3/22
 */
@Service
@Slf4j
public class MyTitleServiceImpl implements MyTitleService {
    @Autowired
    private MyTitleMapper myTitleMapper;
    @Override
    public List<MyTitle> queryMyTitleByUserId(Integer uid) {

        return myTitleMapper.myTitle(uid);
    }

    @Override
    public int updataMyTitleState(Integer mid, Integer state) {
        Map mp = new HashMap<>();
        mp.put("mid", mid);
        mp.put("state",state);
        return myTitleMapper.updateMyTitle(mp);
    }

    @Override
    public int saveMyTitle(MyTitle myTitle) {
        return myTitleMapper.saveMyTitle(myTitle);
    }

    @Override
    public List<MyTitle> myTitleByClassify(Integer uid, Integer category, Integer type) {
        Map mp = new HashMap<>();
        mp.put("uid", uid);
        mp.put("category", category);
        mp.put("type", type);
        return myTitleMapper.myTitleByClassify(mp);
    }

    @Override
    public PageInfo<MyTitle> myTitleByClassify(Integer uid, Integer category, Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MyTitle> myTitles = myTitleByClassify(uid, category, type);
        return new PageInfo<>(myTitles);
    }


    @Override
    public int completeTitle(Integer uid, Integer category,Integer type) {
        Map mp = new HashMap<>();
        mp.put("uid", uid);
        mp.put("category", category);
        mp.put("type", type);
        return myTitleMapper.completeTitle(mp);
    }

    @Override
    public List<MyTitle> queryAllWrongTitle(Integer uid) {

        return myTitleMapper.queryAllWrongTitle(uid);
    }

    @Override
    public PageInfo<MyTitle> queryAllWrongTitle(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MyTitle> myTitles = myTitleMapper.queryAllWrongTitle(uid);
        return new PageInfo<>(myTitles);
    }
}
