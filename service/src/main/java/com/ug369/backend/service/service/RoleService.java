package com.ug369.backend.service.service;

import com.ug369.backend.bean.bean.response.CommonRoleEntry;
import com.ug369.backend.bean.bean.response.RoleEntry;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.service.entity.mysql.Role;
import com.ug369.backend.service.entity.mysql.UserRole;
import com.ug369.backend.service.repository.mysql.RoleRepository;
import com.ug369.backend.service.repository.mysql.UserRoleRepository;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {

//    public enum Type {UNKNOWN, ADMIN, DEVELOPER, MAINTAINER, MONITOR}
//
//    public enum Status {TRUE, FALSE, ALL}

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role findById(long id) {
        return roleRepository.findById(id);
    }


    public List<RoleEntry> getRoleList() {
        Iterable<Role> all = roleRepository.findAll();
        if (all != null){
            ArrayList roles = new ArrayList();
            RoleEntry entry;
            Object[] objects = IteratorUtils.toArray(all.iterator());
            if (objects!= null && objects.length >0){
                for(Object o : objects){
                    entry = new RoleEntry();
                    entry.setText(((Role)o).getName());
                    entry.setValue(((Role)o).getId());
                    roles.add(entry);
                }
            }
            return roles;
        }
        return null;
    }

    public List<CommonRoleEntry> getAll() {
        Iterable<Role> all = roleRepository.findAll();
        if (all != null){
            ArrayList roles = new ArrayList();
            CommonRoleEntry entry;
            Object[] objects = IteratorUtils.toArray(all.iterator());
            if (objects!= null && objects.length >0){
                for(Object o : objects){
                    entry = new CommonRoleEntry();
                    entry.setId(((Role)o).getId());
                    entry.setName(((Role)o).getName());
                    entry.setCode(((Role)o).getCode());
                    entry.setDescription(((Role)o).getDescription());
                    roles.add(entry);
                }
            }
            return roles;
        }
        return null;
    }

    @Transactional
    public void deleteOne(long rid){
        List<UserRole> byRole = userRoleRepository.findByRole(rid);
        if (byRole!=null && byRole.size()>0){
            throw new UserException(UgmsStatus.LOGIC_ERROR,"不能删除，当前角色下还有用户!");
        }
        roleRepository.delete(rid);
    }

    public void updateOrCreate(CommonRoleEntry entry){
        if (entry.getId() == 0L){
            Role role = new Role();
            role.setDescription(entry.getDescription());
            role.setName(entry.getName());
            role.setCode(entry.getCode());
            roleRepository.save(role);
        }else {
            Role role = roleRepository.findOne(entry.getId());
            if (role == null){
                throw new UserException(UgmsStatus.NOT_FOUND," 角色"+entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getName())){
                role.setName(entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getDescription())){
                role.setDescription(entry.getDescription());
            }
            if (!StringUtils.isEmpty(entry.getCode())){
                role.setCode(entry.getCode());
            }
            roleRepository.save(role);
        }
    }
}
