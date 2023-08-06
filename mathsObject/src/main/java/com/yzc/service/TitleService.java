package com.yzc.service;

import com.yzc.entities.Title;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:38 2022/3/22
 */
public interface TitleService {
    List<Title> queryAllTitleByCategory(Integer category);
    List<Title> queryAllTitleByType(Integer type);
    int insertTitle(Title title);
    int updataTitle(Title title);
    int deleteTitle(Integer tid);
    Title getTitleById(Integer tid);
    List<Title> queryAllTitle();
}
