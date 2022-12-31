package com.admin.service;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseRoleDTO;
import com.admin.entity.TBaseRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_role】的数据库操作Service
* @createDate 2022-06-12 22:43:55
*/

public interface TBaseRoleService extends IService<TBaseRole> {
    IPage<TBaseRole> findRolelike(Page<TBaseRole> page,String name);

    List<TBaseRole> selectnotrole(Long id);

    void deleteRoleAll(@RequestParam(value = "ids")List<String> ids);

    Result addRole(TBaseRoleDTO tBaseRoleDTO);


    Result updateRole( @RequestBody TBaseRoleDTO tBaseRoleDTO);

    List<TBaseRole> selectUserRolesByUId(Long id);


}
