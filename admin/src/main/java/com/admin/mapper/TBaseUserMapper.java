package com.admin.mapper;

import com.admin.dto.TBaseUserDTO;
import com.admin.entity.TBaseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author 陈同学
* @description 针对表【t_base_user】的数据库操作Mapper
* @createDate 2022-06-13 22:44:19
* @Entity com.admin.entity.TBaseUser
*/
@Mapper
public interface TBaseUserMapper extends BaseMapper<TBaseUser> {

    @Select("SELECT * FROM t_base_user WHERE CONCAT(NAME) regexp #{name}")
    IPage<TBaseUser> findRolelike(Page<TBaseUser> tBaseUserPage,String name);



    boolean deleteRoleByUser(long id);


    List<TBaseUser> findUserByLoginName(String name);

    List<TBaseUserDTO> selectRoleByUserId1(long id);

    void updateUser(TBaseUserDTO tBaseUserDTO);




}




