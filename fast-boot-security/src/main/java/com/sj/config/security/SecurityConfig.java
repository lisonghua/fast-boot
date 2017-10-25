package com.sj.config.security;

import com.sj.config.security.handler.AuthenticationFailureHandler;
import com.sj.config.security.handler.AuthenticationLogoutSuccessHandler;
import com.sj.config.security.handler.AuthenticationSuccessHandler;
import com.sj.config.security.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.sql.DataSource;


/**
 * Created by yangrd on 2017/7/3.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/reg", "/login", "/app/**", "/plugins/**").permitAll()//访问：无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
            .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
            .csrf()
                .ignoringAntMatchers("/druid/**").disable()
            .formLogin()
                .successHandler(authenticationSuccessHandler()).failureHandler(authenticationFailureHandler())
                .loginProcessingUrl("/login")
                .loginPage("/login").permitAll()
                .and()
            .logout()
                .logoutSuccessHandler(authenticationLogoutSuccessHandler())
                .permitAll()
                .and()
            .rememberMe()
                .rememberMeCookieName("rememberMe")
                .tokenValiditySeconds(24 * 60 * 60 * 30) // expired time = 30 day
                .tokenRepository(persistentTokenRepository())
                .and();
//      默认情况下，CSRF保护已启用。你必须配置包含_csrf令牌的所有的网页来工作。
//      你可以随时禁用CSRF保护。如果在代码中配置： 解决post请无法提交
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    @Bean
    public SimpleUrlLogoutSuccessHandler authenticationLogoutSuccessHandler() {
        return new AuthenticationLogoutSuccessHandler();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
