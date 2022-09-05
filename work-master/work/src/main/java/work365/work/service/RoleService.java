package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work365.work.dto.RoleDto;
import work365.work.model.Role;

@Service
public class RoleService {
    @Autowired
    private RoleDto roleDto;
    public Role createNewRole(Role role){
    return roleDto.save(role);
    }}
