package com.sj;

import com.sj.module.sys.domain.User;
import com.sj.module.sys.repository.UserRepository;
import com.sj.module.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SanjiBootShiroApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        userService.findByLoginName("admin");
        userService.findByLoginName("admin");
        userService.findByLoginName("admin");
        System.out.print("---------------------");
        userRepository.findByLoginName("admin");
        userRepository.findByLoginName("admin");
        userRepository.findByLoginName("admin");
        userRepository.findByLoginName("admin");
        User user = new User();
        user.setLoginName("abc").setPassword("abc");
        User user1 = new User();
        user1.setLoginName("abcd").setLoginName("abcd");

        userRepository.save(Arrays.asList(user, user1));

        userRepository.findAll(Arrays.asList(1L, 2L));
    }

}
