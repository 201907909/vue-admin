package com.admin.service;

import com.admin.common.lang.Result;
import com.admin.entity.TBaseRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_role_menu】的数据库操作Service
* @createDate 2022-06-13 22:43:52
*/

public interface TBaseRoleMenuService extends IService<TBaseRoleMenu> {
    boolean deleteRoleMenuById(long id);


    Result addrolemenu(Long roleid, List<Long> menuids);

    Result deleteRoleByroleIdAndUserId(Long roleid, List<Long> menuids);


}
