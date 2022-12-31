package com.admin.service;

import com.admin.common.lang.Result;
import com.admin.entity.TBaseRole;
import com.admin.entity.TBaseUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_user_role】的数据库操作Service
* @createDate 2022-06-13 22:44:25
*/
public interface TBaseUserRoleService extends IService<TBaseUserRole> {
    Result adduserrole(Long roleId, List<Long> roleids);

    Result deleteRoleByroleIdAndUserId(Long userId, List<Long> roleids);


}
