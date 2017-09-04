package com.ug369.backend.service.repository.rdbsupport;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface RDBRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, MybatisSupport {

}
