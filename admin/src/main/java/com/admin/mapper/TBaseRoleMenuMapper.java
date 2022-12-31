package com.admin.mapper;

import com.admin.entity.TBaseRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
* @author 陈同学
* @description 针对表【t_base_role_menu】的数据库操作Mapper
* @createDate 2022-06-13 22:43:52
* @Entity com.admin.entity.TBaseRoleMenu
*/
public interface TBaseRoleMenuMapper extends BaseMapper<TBaseRoleMenu> {

    void deleteRoleMenu(long id);

    @Insert("insert into t_base_role_menu(role_id,menu_id) values (#{roleId},#{menuId})")
    void saveRoleAndMenu(@Param("roleId") Long userId, @Param("menuId") Long menuId);

    @Delete(("delete from t_base_role_menu WHERE t_base_role_menu.ROLE_ID=#{roleId} AND t_base_role_menu.menu_id =#{menuId}"))
    void deleteMenuBymenuIdAndroleId(@Param("roleId") Long roleId,@Param("menuId") Long menuId);

}




