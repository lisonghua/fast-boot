package com.lish.dongfang.vote.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DongFangVoteTestApplication.class)
@AutoConfigureMockMvc
public class BaseVoteTest {
	
	/**
	 * 模拟MVC对象
	 */
	@Autowired
    protected MockMvc mockMvc;
	
	/**
	 * 远端rest服务测试客户端
	 */
	@Autowired
	protected TestRestTemplate restTemplate;
  
    /**
     * 在测试开始前初始化工作    
     */
    @Before 
    public void setup() {    
    	
    }
    
    @Test
    public void loadContext() {
    	
    }
    
    @TestConfiguration
    static class Config {
        @Bean
        public TestRestTemplate testRestTemplate() {
            return new TestRestTemplate(new RestTemplateBuilder().build());
        }
    }
}
