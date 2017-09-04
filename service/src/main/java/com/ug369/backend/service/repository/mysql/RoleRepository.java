package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Role;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends RDBRepository<Role, Long> {


    Role findByName(String name);

    Role findById(long id);
}
