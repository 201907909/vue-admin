package com.admin.mapper;

import com.admin.entity.TBaseRole;
import com.admin.entity.TBaseUserRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_user_role】的数据库操作Mapper
* @createDate 2022-06-13 22:44:25
* @Entity com.admin.entity.TBaseUserRole
*/
public interface TBaseUserRoleMapper extends BaseMapper<TBaseUserRole> {

    @Insert("insert into t_base_user_role(user_id,role_id) values (#{userId},#{roleId})")
    void saveUserAndRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Delete(("delete from t_base_user_role WHERE t_base_user_role.ROLE_ID=#{roleId} AND t_base_user_role.USER_ID =#{userId}"))
    void deleteRoleByroleIdAndUserId(@Param("userId") Long userId,@Param("roleId") Long roleId);
}




