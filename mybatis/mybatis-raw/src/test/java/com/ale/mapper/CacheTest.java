package com.ale.mapper;

import com.ale.entity.Student;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author alewu
 * @date 2020/9/19
 */
@SpringBootTest
class CacheTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
        //    @Transactional(rollbackFor = Throwable.class)
    void testGetOneCache() {
        System.out.println("一级缓存范围: " + sqlSessionFactory.getConfiguration().getLocalCacheScope());
        Student one = studentMapper.getOne(1);
        System.out.println("第一次查询" + one);
        one.setName("xxx");
        Student two = studentMapper.getOne(1);
        System.out.println("第二次查询" + two);
    }

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("一级缓存范围: " + sqlSessionFactory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + sqlSessionFactory.getConfiguration().isCacheEnabled());
    }
}
