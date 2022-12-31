package com.admin.mapper;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseMenuDTO;
import com.admin.entity.TBaseMenu;
import com.admin.entity.TBaseRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_menu】的数据库操作Mapper
* @createDate 2022-06-13 22:40:59
* @Entity com.admin.entity.TBaseMenu
*/
@Mapper
public interface TBaseMenuMapper extends BaseMapper<TBaseMenu> {

    void deleteMenu(long id);

    IPage<TBaseMenu> findLike(IPage<TBaseMenu> page, String name);

    TBaseMenu findMenu(long id);

    List<TBaseMenu> findMenuByName(String name);

    void updateMenu(TBaseMenuDTO tBaseMenuDTO);

    List<TBaseMenu> selectnotmenu(Long id);

    List<TBaseMenu>  selectRoleMenus(Long id);

}




