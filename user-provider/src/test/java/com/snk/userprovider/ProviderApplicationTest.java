package com.snk.userprovider;

import com.snk.userprovider.dao.CourseMapper;
import com.snk.userprovider.pojo.domain.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/7/4
 */
@SpringBootTest
public class ProviderApplicationTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void test() {

        for (int i = 0; i <10 ; i++) {
            Course course = new Course();
            course.setCname("shardingspere");
            course.setUserId(Long.valueOf(1000+i));
            course.setCstatus("1");
            courseMapper.insert(course);
        }
    }
}
