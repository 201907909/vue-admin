package com.admin.controller;



import com.admin.common.lang.Result;
import com.admin.dto.TBaseMenuDTO;
import com.admin.dto.TBaseRoleDTO;
import com.admin.entity.TBaseMenu;
import com.admin.entity.TBaseRole;
import com.admin.entity.TBaseUser;
import com.admin.mapper.TBaseRoleMapper;
import com.admin.service.TBaseRoleMenuService;
import com.admin.service.TBaseRoleService;


import com.admin.service.TBaseUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class TBaseRoleController {
    @Resource
    TBaseRoleService tBaseRoleService;
    @Resource
    TBaseRoleMapper tBaseRoleMapper;
    @Resource
    TBaseRoleMenuService tBaseRoleMenuService;
    @Resource
    TBaseUserRoleService tBaseUserRoleService;


    /**
     * 查询所用角色
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/getRoles")

    public Result roles(@RequestParam(defaultValue = "1") Integer currentPage) throws Exception {

        Page page = new Page(currentPage, 5);
        IPage<TBaseRole> pageData = tBaseRoleService.page(page, new QueryWrapper<TBaseRole>().orderByAsc("ID"));

        return Result.succ(pageData);
    }

    /**
     * @param name
     * @param currentPage
     * @return
     */
    @GetMapping("findrolelike")
    public Result findlike(@RequestParam(value = "name") String name, @RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 2);
        IPage<TBaseRole> tBaseRoleIPage = tBaseRoleService.findRolelike(page, name);
        return Result.succ(tBaseRoleIPage);
    }

    @GetMapping("/selectnotrole")
    public Result selectnotrole(Long id) {
        List<TBaseRole> selectnotrole = tBaseRoleService.selectnotrole(id);
        return Result.succ(selectnotrole);
    }

    /**
     * 为角色添加菜单
     *
     * @param roleId
     * @param menuids
     * @return
     */
    @DeleteMapping("/addRolemenu")
    public Result addrolemenu(Long roleId, @RequestParam(value = "menuids") List<Long> menuids) {
        Result result = tBaseRoleMenuService.addrolemenu(roleId, menuids);
        return result;

    }


    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteRole")
    public Result deleteMenu(@RequestParam(value = "ids") List<String> ids) {
        tBaseRoleService.deleteRoleAll(ids);
        tBaseRoleService.removeByIds(ids);
        return Result.succ("删除成功");
    }

    /**
     * 新增角色
     *
     * @param
     * @return
     */

    @PostMapping("/addNewRole")

    public Result addMenu(@RequestBody TBaseRoleDTO tBaseRoleDTO) {

        return tBaseRoleService.addRole(tBaseRoleDTO);
    }


    /**
     * 更新角色
     *
     * @param tBaseRoleDTO
     * @return
     */
    @PostMapping("/update")
    public Result updateRole(@RequestBody TBaseRoleDTO tBaseRoleDTO) {

        return tBaseRoleService.updateRole(tBaseRoleDTO);
    }

    /**
     * 通过id查找角色
     *
     * @param id
     * @return
     */
    @GetMapping("/findIdRole")
    public Result findIdRole(@RequestParam(value = "id") long id) {


        return Result.succ(tBaseRoleMapper.selectById(id));
    }

    /**
     * 角色信息及所属的菜单查询
     * @param id
     * @return
     */
    @PostMapping("/findRoleMenus")
    public Result find(@RequestParam(value = "id") long id) {

        return Result.succ(tBaseRoleMapper.selectMenuByRoleId1(id));


    }

    /**
     * 查询用户拥有的角色
     * @param id
     * @return
     */
    @GetMapping("/selectUserRoles")
    public Result selectUserRoles(Long id){
        List<TBaseRole> tBaseRoles = tBaseRoleService.selectUserRolesByUId(id);
        return Result.succ(tBaseRoles);
    }

    /**
     * 解绑用户角色
     *
     * @param userId
     * @param roleids
     * @return
     */
    @DeleteMapping("deleteRoleByroleIdAndUserId")
    public Result deleteRoleByroleIdAndUserId(Long userId,@RequestParam(value = "roleids") List<Long> roleids){
        Result result = tBaseUserRoleService.deleteRoleByroleIdAndUserId(userId,roleids);
        return result;
    }

}
