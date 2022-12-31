package com.admin.service.impl;

import com.admin.common.lang.Result;
import com.admin.mapper.TBaseRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TBaseRoleMenu;
import com.admin.service.TBaseRoleMenuService;
import com.admin.mapper.TBaseRoleMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_role_menu】的数据库操作Service实现
* @createDate 2022-06-13 22:43:52
*/
@Service
public class TBaseRoleMenuServiceImpl extends ServiceImpl<TBaseRoleMenuMapper, TBaseRoleMenu>
    implements TBaseRoleMenuService{
    @Resource
    TBaseRoleMenuMapper tBaseRoleMenuMapper;
    /**
     * 删除角色菜单关系表
     * @param id
     * @return
     */
    @Override
    public boolean deleteRoleMenuById(long id) {
        tBaseRoleMenuMapper.deleteRoleMenu(id);
        return true;
    }
    /**
     * 添加角色对应的菜单
     */

    @Override
    public Result addrolemenu(Long roleid, List<Long> menuids) {
        for (Long menuid:menuids){
            tBaseRoleMenuMapper.saveRoleAndMenu(roleid,menuid);
        }
        return Result.succ("添加菜单成功");
    }

    @Override
    public Result deleteRoleByroleIdAndUserId(Long roleid, List<Long> menuids) {
        for (Long menuid : menuids) {
            tBaseRoleMenuMapper.deleteMenuBymenuIdAndroleId(roleid,menuid);

        }
        return Result.succ("解绑菜单成功");

    }
}




