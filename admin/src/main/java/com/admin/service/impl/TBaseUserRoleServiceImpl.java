package com.admin.service.impl;

import com.admin.common.lang.Result;
import com.admin.entity.TBaseRole;
import com.admin.entity.TBaseUser;
import com.admin.mapper.TBaseRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TBaseUserRole;
import com.admin.service.TBaseUserRoleService;
import com.admin.mapper.TBaseUserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_user_role】的数据库操作Service实现
* @createDate 2022-06-13 22:44:25
*/
@Service
public class TBaseUserRoleServiceImpl extends ServiceImpl<TBaseUserRoleMapper, TBaseUserRole>
    implements TBaseUserRoleService{

    @Resource
    TBaseUserRoleMapper tBaseUserRoleMapper;

    @Resource
    TBaseRoleMapper tBaseRoleMapper;
    /**
     * 为用户添加角色
     */

    @Override
    public Result adduserrole(Long userId, List<Long> roleids){

        for (Long roleid : roleids) {
            tBaseUserRoleMapper.saveUserAndRole(userId, roleid);
        }


        return Result.succ(roleids);
    }

    /**
     * 解绑用户角色
     * @param userId
     * @param roleids
     * @return
     */
    @Override
    public Result deleteRoleByroleIdAndUserId(Long userId, List<Long> roleids) {
        for (Long roleid : roleids) {
            tBaseUserRoleMapper.deleteRoleByroleIdAndUserId(userId, roleid);
        }

        return Result.succ("解绑角色成功");
    }



}




