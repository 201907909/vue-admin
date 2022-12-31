package com.admin.service;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseUserDTO;
import com.admin.entity.TBaseUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_user】的数据库操作Service
* @createDate 2022-06-13 22:44:19
*/
public interface TBaseUserService extends IService<TBaseUser> {
    IPage<TBaseUser> findUserlike(Page<TBaseUser> page, String name);

    void deleteUserAll(@RequestParam(value = "ids") List<String> ids);

    Result addUser(TBaseUserDTO tBaseUserDTO);

    Result updateUser( @RequestBody TBaseUserDTO tBaseUserDTO);



}
