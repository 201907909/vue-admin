package com.admin.mapper;

import com.admin.dto.TBaseMenuDTO;
import com.admin.dto.TBaseRoleDTO;
import com.admin.entity.TBaseRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_role】的数据库操作Mapper
* @createDate 2022-06-12 22:43:55
* @Entity com.admin.entity.TBaseRole
*/
@Mapper
public interface TBaseRoleMapper extends BaseMapper<TBaseRole> {

    @Select("SELECT * FROM t_base_role WHERE  CONCAT(name) regexp #{name}")
    IPage<TBaseRole> findRolelike(Page<TBaseRole> tBaseRolePage,String name);


    List<TBaseRole> selectnotrole(Long id);

    void deleteRoleByUserRole(long id);
    void deleteRoleByRoleMenu(long id);

    List<TBaseRole> findRoleByName(String name);

    void updateRole(TBaseRoleDTO tBaseRoleDTO);

    List<TBaseRoleDTO> selectMenuByRoleId1(long id);

    List<TBaseRole> selectUserRoles(Long id);




}





