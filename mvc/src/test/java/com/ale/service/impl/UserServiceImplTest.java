//package com.ale.cache.service.impl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.when;
//@SpringBootTest
//class UserServiceImplTest {
//    @Mock
//    UserMapper userMapper;
//    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when
//    // 'mock-maker-inline' option is not set
//    @InjectMocks
//    UserServiceImpl userServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        User t = new User();
//        t.setId(1);
//        when(userMapper.selectById(1)).thenReturn(t);
//    }
//
//    @Test
//    void testSelectUser() {
//        User result = userServiceImpl.selectUser("java");
//        User expected = new User();
//        expected.setId(1);
//        expected.setName("java");
//        Assertions.assertEquals(expected, result);
//    }
//}
//
////Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme