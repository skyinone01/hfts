package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.RoleResource;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleResourceRepository extends RDBRepository<RoleResource, Long> {

    List<RoleResource> findByRole(long role);

    RoleResource findByRoleAndResource(long role,long resource);

    void deleteByRole(long rid);


}
