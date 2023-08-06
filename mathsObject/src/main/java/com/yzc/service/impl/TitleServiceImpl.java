package com.yzc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.entities.Title;
import com.yzc.mapper.TitleMapper;
import com.yzc.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yangzicheng
 * @Date Created in 16:39 2022/3/22
 */
@Service
@Slf4j
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleMapper titleMapper;
    @Override
    public List<Title> queryAllTitleByCategory(Integer category) {
        QueryWrapper<Title> qw = new QueryWrapper<>();
        qw.like("title_category",category);
        List<Title> titles = titleMapper.selectList(qw);
        return titles;
    }

    @Override
    public List<Title> queryAllTitleByType(Integer type) {
        QueryWrapper<Title> qw = new QueryWrapper<>();
        qw.like("title_type",type);
        List<Title> titles = titleMapper.selectList(qw);
        return titles;
    }

    @Override
    public int insertTitle(Title title) {

        return titleMapper.insert(title);
    }

    @Override
    public int updataTitle(Title title) {
        QueryWrapper<Title> qw = new QueryWrapper<>();
        // update ..... where cid = ？
        qw.eq("title_id",title.getTitleId());
        return titleMapper.update(title,qw);
    }

    @Override
    public int deleteTitle(Integer tid) {
        QueryWrapper<Title> qw = new QueryWrapper<>();
        qw.eq("title_id",tid);
        return titleMapper.delete(qw);
    }

    @Override
    public Title getTitleById(Integer tid) {
        QueryWrapper<Title> qw = new QueryWrapper<>();
        qw.eq("title_id",tid);
        return titleMapper.selectOne(qw);
    }

    @Override
    public List<Title> queryAllTitle() {
        return titleMapper.selectList(null);
    }


}
