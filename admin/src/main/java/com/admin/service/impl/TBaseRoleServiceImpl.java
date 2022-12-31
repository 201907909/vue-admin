package com.admin.service.impl;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseRoleDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TBaseRole;
import com.admin.service.TBaseRoleService;
import com.admin.mapper.TBaseRoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 陈同学
* @description 针对表【t_base_role】的数据库操作Service实现
* @createDate 2022-06-12 22:43:55
*/
@Service
public class TBaseRoleServiceImpl extends ServiceImpl<TBaseRoleMapper, TBaseRole>
    implements TBaseRoleService{

    @Resource
    private TBaseRoleMapper tBaseRoleMapper;

    @Resource
    private TBaseRoleDTO tBaseRoleDTO;


    @Override
    public IPage<TBaseRole> findRolelike(Page<TBaseRole> page,String str) {
        tBaseRoleMapper.findRolelike(page,str);
        return page;

    }
    @Override
    public List<TBaseRole> selectnotrole(Long id) {
        List<TBaseRole> selectnotrole = tBaseRoleMapper.selectnotrole(id);
        return selectnotrole;
    }

    @Override
    public void deleteRoleAll(@RequestParam(value = "ids")List<String> ids) {

        List<Long> idsList = ids.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        Iterator<Long> idsList1 = idsList.iterator();
        while (idsList1.hasNext()) {
            Long id = idsList1.next();
            tBaseRoleMapper.deleteRoleByRoleMenu(id);
            tBaseRoleMapper.deleteRoleByUserRole(id);



        }

    }

    @Override
    public Result addRole(TBaseRoleDTO tBaseRoleDTO){
        List<TBaseRole> roleByName = tBaseRoleMapper.findRoleByName(tBaseRoleDTO.getName());
        if (roleByName.size() == 0) {
            TBaseRole tBaseRole= new TBaseRole();
            BeanUtils.copyProperties(tBaseRoleDTO, tBaseRole);
            save(tBaseRole);
            return Result.succ("成功添加角色");
        } else {

            return Result.succ("角色已存在，添加失败");
        }

    }

    @Override
    public Result updateRole( @RequestBody TBaseRoleDTO tBaseRoleDTO) {
        List<TBaseRole> roleByName2 = tBaseRoleMapper.findRoleByName(tBaseRoleDTO.getName());

        if (roleByName2.size() == 0) {
            tBaseRoleMapper.updateRole(tBaseRoleDTO);
            return Result.succ("修改角色成功");
        } else {
            return Result.succ("角色名已存在，修改失败");
        }


    }

    /**
     * 查询用户拥有的角色
     * @param id
     * @return
     */
    @Override
    public List<TBaseRole> selectUserRolesByUId(Long id) {
        List<TBaseRole> tBaseRoles = tBaseRoleMapper.selectUserRoles(id);
        return tBaseRoles;

    }






}







