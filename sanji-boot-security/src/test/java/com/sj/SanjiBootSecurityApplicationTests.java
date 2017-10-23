package com.sj;

import com.sj.modules.sys.domain.Role;
import com.sj.modules.sys.repository.MenuRepository;
import com.sj.modules.sys.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SanjiBootSecurityApplicationTests {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void contextLoads() {
        menuRepository.findAll();
    }

}
