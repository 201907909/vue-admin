package com.admin.service;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseMenuDTO;
import com.admin.entity.TBaseMenu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_menu】的数据库操作Service
* @createDate 2022-06-13 22:40:59
*/
public interface TBaseMenuService extends IService<TBaseMenu> {

    void deleteMenuById(long id);
    IPage<TBaseMenu> findMenulike(IPage<TBaseMenu> page,String name);
    TBaseMenu findMenuById(long id);

    Result addMenu(TBaseMenuDTO tBaseMenuDTO);

    Result updateMenu( @RequestBody TBaseMenuDTO tBaseMenuDTO);

    List<TBaseMenu> selectnotmenu(Long id);

    List<TBaseMenu> selectRoleMenusByRID(Long id);



}
