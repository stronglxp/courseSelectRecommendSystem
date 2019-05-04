package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.CollegeMapper;
import com.codeliu.course_select_system.entity.College;
import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:52
 * @Version 1.0
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> getCollegeList() {

        List<College> collegeList = null;
        collegeList = collegeMapper.getCollegeList();

        return collegeList;
    }

    @Override
    public Integer getCountCollege() {
        return collegeMapper.getCountCollege();
    }

    @Override
    public Integer getCollegeById(Integer collegeId) {
        Integer id = null;
        id = collegeMapper.getCollegeById(collegeId);
        return id;
    }

    @Override
    public List<Map<String, Object>> getCollegeListByPage(Integer toPageNo) {
        PageVO pageVO = new PageVO();
        pageVO.setToPageNo(toPageNo);

        return collegeMapper.getCollegeListByPage(pageVO);
    }

    @Override
    public List<Map<String, Object>> findByCollName(String collegeName) {
        return collegeMapper.findByCollName(collegeName);
    }

    @Override
    public Integer addCollege(String collegeName) {
        Integer result = null;
        result = collegeMapper.addCollege(collegeName);
        return result;
    }

    @Override
    public String getCollegeName(String collegeName) {
        String cn = null;
        cn = collegeMapper.getCollegeName(collegeName);
        return cn;
    }

    @Override
    public Integer updateCollege(College college) {
        Integer result = null;

        result = collegeMapper.updateCollege(college);

        return result;
    }

    @Override
    public Integer getCollegeByName(String collegeName) {
        return collegeMapper.getCollegeByName(collegeName);
    }
}
