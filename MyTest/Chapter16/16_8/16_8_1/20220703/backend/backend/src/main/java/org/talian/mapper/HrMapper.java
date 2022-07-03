package org.talian.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.talian.bean.Hr;
import org.talian.bean.Role;

import java.util.List;

@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String username);

    List<Role> getRoleByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);
}