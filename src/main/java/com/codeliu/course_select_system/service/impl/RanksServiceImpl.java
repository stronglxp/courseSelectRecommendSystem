package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.RanksMapper;
import com.codeliu.course_select_system.entity.Ranks;
import com.codeliu.course_select_system.service.RanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RanksServiceImpl implements RanksService {

    @Autowired
    private RanksMapper ranksMapper;

    @Override
    public List<Map<String, Object>> getRankList() {
        return ranksMapper.getRankList();
    }

    @Override
    public Integer updateRank(Ranks ranks) {
        return ranksMapper.updateRank(ranks);
    }

    @Override
    public Integer addRank(Ranks ranks) {
        return ranksMapper.addRank(ranks);
    }

    @Override
    public Integer deleteRank(Ranks ranks) {
        return ranksMapper.deleteRank(ranks);
    }
}
