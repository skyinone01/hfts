package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import com.ug369.backend.service.entity.mysql.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends RDBRepository<Resource, Long> {

	Resource findById(long id);
}
