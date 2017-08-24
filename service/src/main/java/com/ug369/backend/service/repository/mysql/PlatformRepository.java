package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Platform;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roy on 2017/3/10.
 */
@Repository
public interface PlatformRepository extends RDBRepository<Platform, Long> {

	Platform findByName(String name);

}
