package com.yzc.service;

import com.github.pagehelper.PageInfo;
import com.yzc.entities.CollectionTitle;
import com.yzc.entities.MyTitle;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:38 2022/3/22
 */
public interface MyTitleService {
    List<MyTitle> queryMyTitleByUserId(Integer uid);
    int updataMyTitleState(Integer mid, Integer state);
    int saveMyTitle(MyTitle myTitle);
    
    List<MyTitle> myTitleByClassify(Integer uid, Integer category, Integer type);
    PageInfo<MyTitle> myTitleByClassify(Integer uid, Integer category, Integer type,Integer pageNum, Integer pageSize);
    int completeTitle(Integer uid, Integer category,Integer type);
    List<MyTitle> queryAllWrongTitle(Integer uid);
    PageInfo<MyTitle> queryAllWrongTitle(Integer uid, Integer pageNum, Integer pageSize);

}
