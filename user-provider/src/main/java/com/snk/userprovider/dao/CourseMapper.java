package com.snk.userprovider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snk.userprovider.pojo.domain.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 课程dao
 * @Date : 2021/7/4
 */
@Mapper
public interface CourseMapper  extends BaseMapper<Course> {
}
