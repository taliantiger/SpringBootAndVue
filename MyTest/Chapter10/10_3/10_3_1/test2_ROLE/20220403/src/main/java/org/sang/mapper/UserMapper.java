package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Role;
import org.sang.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);
}
