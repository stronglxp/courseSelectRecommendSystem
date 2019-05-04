package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.College;
import com.codeliu.course_select_system.entity.PageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author liuxiaoping
 * @Description College  dao
 * @Date 9:08 2019/2/1
 * @Param
 * @return
 **/
@Mapper
public interface CollegeMapper {

    /**
     * @Author liuxiaoping
     * @Description 查询所有的院系
     * @Date 14:00 2019/2/16
     * @Param []
     * @return java.util.List<College>
     **/
    List<College> getCollegeList();

    /**
     * 以分页形式获取院系信息
     * @param pageVO
     * @return
     */
    List<Map<String, Object>> getCollegeListByPage(PageVO pageVO);

    /**
     * 获取院系总数
     * @return
     */
    Integer getCountCollege();

    /**
     * 获取院系id
     * @param collegeId
     * @return
     */
    Integer getCollegeById(Integer collegeId);

    /**
     * 院系名搜索院系
     * @param collegeName
     * @return
     */
    List<Map<String, Object>> findByCollName(String collegeName);

    /**
     * 增加一个院系
     * @param collegeName
     * @return
     */
    Integer addCollege(String collegeName);

    /**
     * 查看院系名称是否存在
     * @param collegeName
     * @return
     */
    String getCollegeName(String collegeName);

    /**
     * 更新院系信息
     * @param college
     * @return
     */
    Integer updateCollege(College college);

    /**
     * 通过院系名称查询院系id
     * @param collegeName
     * @return
     */
    Integer getCollegeByName(String collegeName);
}