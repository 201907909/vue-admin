package com.admin.controller;


import com.admin.common.lang.Result;
import com.admin.dto.TBaseMenuDTO;
import com.admin.entity.TBaseMenu;
import com.admin.mapper.TBaseMenuMapper;
import com.admin.mapper.TBaseRoleMenuMapper;
import com.admin.service.TBaseMenuService;
import com.admin.service.TBaseRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/menu")
public class TBaseMenuController {
    @Resource
    TBaseMenuService tBaseMenuService;
    @Resource
    TBaseMenuMapper tBaseMenuMapper;
    @Resource
    TBaseRoleMenuService tBaseRoleMenuService;
    @Resource
    TBaseRoleMenuMapper tBaseRoleMenuMapper;
    /**
     * 获取菜单数据进行降序
     * @param currentPage
     * @param tBaseMenuDTO
     * @return
     */
    @GetMapping("/getMenus")
    public Result Menus(@RequestParam(defaultValue = "1") Integer currentPage,TBaseMenuDTO tBaseMenuDTO) {

        Page page = new Page(currentPage, 4);
        IPage<TBaseMenu> pageData = tBaseMenuService.page(page, new QueryWrapper<TBaseMenu>()
                .eq(StringUtils.isNotBlank(tBaseMenuDTO.getName()),"NAME",tBaseMenuDTO.getName())
                .eq(StringUtils.isNotBlank(tBaseMenuDTO.getIco()),"ico",tBaseMenuDTO.getIco())
                .orderByDesc("update_time")
        );
        return Result.succ(pageData);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("/delMenuById")
    public Result deleteMenuById(@RequestParam(value = "id") long id) {

        tBaseRoleMenuService.deleteRoleMenuById(id);
        if (tBaseRoleMenuService.deleteRoleMenuById(id)==true){
            tBaseMenuService.deleteMenuById(id);
        }
        return Result.succ("删除成功");
    }

    /**
     * 关键字搜索菜单
     * @param name
     * @param currentPage
     * @return
     */
    @GetMapping("/findmenulike")
    public Result find(@RequestParam(value = "name") String name,@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage, 5);

        IPage<TBaseMenu> tBaseMenuIPage=tBaseMenuService.findMenulike(page,name);
        return Result.succ(tBaseMenuIPage);
    }

    /**
     * id查找菜单
     * @param id
     * @return
     */
    @GetMapping("/findMenuById")
    public Result findMenu(@RequestParam(value = "id") long id){

        return Result.succ(tBaseMenuService.findMenuById(id));
    }

    /**
     *新增菜单
     * @param tBaseMenuDTO
     * @return
     */
    @PostMapping("/addNewMenu")
    public Result addMenu(@RequestBody TBaseMenuDTO tBaseMenuDTO){
        return tBaseMenuService.addMenu(tBaseMenuDTO);
    }

    @PostMapping("/update")
    public Result updateMenu(@RequestBody TBaseMenuDTO tBaseMenuDTO){

        return tBaseMenuService.updateMenu(tBaseMenuDTO);
    }

    /**
     * 批量删除菜单
     * @param ids
     * @return
     */

    @DeleteMapping("/deleteMenu")
    public Result deleteMenu(@RequestParam(value = "ids")List<String> ids){
        List<Long> idsList = ids.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        Iterator<Long> idsList1 = idsList.iterator();
        while (idsList1.hasNext()) {
            Long id = idsList1.next();
            tBaseRoleMenuMapper.deleteRoleMenu(id);
        }
        tBaseMenuService.removeByIds(ids);
        return  Result.succ("删除成功");
    }

    /**
     * 查询角色没有的菜单
     * @param id
     * @return
     */
    @GetMapping("selectnotmenu")
    public Result selectnotmenu(Long id){
        List<TBaseMenu> selectnotmenu = tBaseMenuService.selectnotmenu(id);
        return Result.succ(selectnotmenu);
    }

    /**
     * 查询角色的拥有的菜单
     * @param id
     * @return
     */
    @GetMapping("selectRoleMenus")
    public Result selectRoleMenus(Long id){
        List<TBaseMenu> tBaseMenus = tBaseMenuService.selectRoleMenusByRID(id);
        return Result.succ(tBaseMenus);
    }

    /**
     * 解绑角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @DeleteMapping("deleteRoleByroleIdAndUserId")
    public Result deleteRoleByroleIdAndUserId(Long roleId,@RequestParam(value = "menuIds") List<Long> menuIds){
        Result result = tBaseRoleMenuService.deleteRoleByroleIdAndUserId(roleId,menuIds);
        return result;
    }





}
