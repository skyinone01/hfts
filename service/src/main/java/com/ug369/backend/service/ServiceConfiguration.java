package com.ug369.backend.service;

import com.ug369.backend.service.repository.mysql.MysqlRepositoryMarker;
import com.ug369.backend.service.repository.rdbsupport.RDBRepositoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = MysqlRepositoryMarker.class,
		repositoryFactoryBeanClass = RDBRepositoryBean.class)
public class ServiceConfiguration {

}
