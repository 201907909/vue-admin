package com.admin.service.impl;
import com.admin.common.lang.Result;
import com.admin.dto.TBaseMenuDTO;
import com.admin.mapper.TBaseRoleMenuMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TBaseMenu;
import com.admin.service.TBaseMenuService;
import com.admin.mapper.TBaseMenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_menu】的数据库操作Service实现
* @createDate 2022-06-13 22:40:59
*/
@Service
public class TBaseMenuServiceImpl extends ServiceImpl<TBaseMenuMapper, TBaseMenu>
    implements TBaseMenuService{
@Resource
TBaseMenuMapper tBaseMenuMapper;
@Resource
    TBaseRoleMenuMapper tBaseRoleMenuMapper;

    /**
     * 删除菜单
     * @param id
     */
    @Override
    public void deleteMenuById(long id){
        tBaseMenuMapper.deleteMenu(id);
    }
    /**
     * 菜单的模糊查询
     * @param page
     * @param name
     * @return
     */
    @Override
    public IPage<TBaseMenu> findMenulike(IPage<TBaseMenu> page, String name){

        return tBaseMenuMapper.findLike(page,name);

    }

    @Override
    public TBaseMenu findMenuById(long id){


        return tBaseMenuMapper.findMenu(id);
    }



    @Override
    public Result addMenu(TBaseMenuDTO tBaseMenuDTO) {
        List<TBaseMenu> menuByName = tBaseMenuMapper.findMenuByName(tBaseMenuDTO.getName());

        if (menuByName.size() == 0) {
            TBaseMenu tBaseMenu = new TBaseMenu();
            BeanUtils.copyProperties(tBaseMenuDTO, tBaseMenu);
            save(tBaseMenu);
            return Result.succ("成功添加菜单");
        } else {

            return Result.succ("菜单已存在，添加失败");
        }

    }

    @Override
    public Result updateMenu( @RequestBody TBaseMenuDTO tBaseMenuDTO) {
        List<TBaseMenu> menuByName1 = tBaseMenuMapper.findMenuByName(tBaseMenuDTO.getName());

        if (menuByName1.size() == 0) {
            tBaseMenuMapper.updateMenu(tBaseMenuDTO);
            return Result.succ("修改成功");
        } else {
            return Result.succ("菜单已存在，修改失败");
        }


    }
    @Override
    public List<TBaseMenu> selectnotmenu(Long id) {
        List<TBaseMenu> selectnotmenu = tBaseMenuMapper.selectnotmenu(id);
        return selectnotmenu;
    }

    @Override
    public List<TBaseMenu> selectRoleMenusByRID(Long id) {
        List<TBaseMenu> tBaseMenus = tBaseMenuMapper.selectRoleMenus(id);
        return tBaseMenus;

    }

}




