package com.ug369.backend.outerapi.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 取消认证filer的自动注册
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(authenticationFilter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationFilter, ExceptionTranslationFilter.class);

//        http.authorizeRequests().antMatchers("/**/**/*","/*","/**/*").permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .headers().disable();
//				.authorizeRequests();
//				.authorizeRequests()
//				.antMatchers("demo/**").permitAll()
//				.antMatchers("/testdev").permitAll()
//				.anyRequest().hasAnyRole();


        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}