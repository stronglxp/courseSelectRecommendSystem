package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.Ranks;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author liuxiaoping
 * @Description Ranks dao
 * @Date 9:09 2019/2/1
 * @Param
 * @return
 **/
@Mapper
public interface RanksMapper {

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