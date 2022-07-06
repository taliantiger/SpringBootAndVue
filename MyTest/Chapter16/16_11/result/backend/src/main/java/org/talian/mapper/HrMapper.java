package org.talian.mapper;


import org.apache.ibatis.annotations.Mapper;
//import org.springframework.data.repository.query.Param;
import org.apache.ibatis.annotations.Param;
import org.talian.bean.Hr;
import org.talian.bean.Role;

import java.util.List;

//@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String username);



    /**
     * 这里单词少些了一个字母  s ，导致无法正确识别，
     * 最终导致在线聊天界面获取数据失败，服务器报错
     *
         FriendChat.vue?fc25:119 Uncaught (in promise)
         TypeError: Cannot read properties of undefined (reading 'data')
         at eval (FriendChat.vue?fc25:119:1)
     * @param id
     * @return
     */
//    List<Role> getRoleByHrId(Long id);
    List<Role> getRolesByHrId(Long id);



    int hrReg(@Param("username") String username, @Param("password") String password);



    /**
     * 作者在这里引用的  Param 包，来源是  import org.apache.ibatis.annotations.Param;
     *  并不是  import org.springframework.data.repository.query.Param;
     *
     *  我最开始引用包引用错了，引用的spring 的 Param; 而不是 apache 的 Param;
     *  可导致无法正确识别对应的 参数（Param）,
     *  最终导致在线聊天界面获取数据失败，服务器报错
     * @param keywords
     * @return
     */
    //List<Hr> getHrsByKeywords(@Param("keywords") String keywords);
    List<Hr> getHrsByKeywords(@org.apache.ibatis.annotations.Param("keywords") String keywords);




    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}