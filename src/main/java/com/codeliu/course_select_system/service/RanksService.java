package com.codeliu.course_select_system.service;

import com.codeliu.course_select_system.entity.Ranks;

import java.util.List;
import java.util.Map;

/**
 * ranks service
 */
public interface RanksService {

    /**
     * 获取所有评分数据
     * @return
     */
    List<Map<String, Object>> getRankList();

    /**
     * 修改总评分
     * @param ranks
     * @return
     */
    Integer updateRank(Ranks ranks);

    /**
     * 增加
     * @param ranks
     * @return
     */
    Integer addRank(Ranks ranks);

    /**
     * 删除
     * @param ranks
     * @return
     */
    Integer deleteRank(Ranks ranks);
}
