package com.admin.service.impl;

import com.admin.common.lang.Result;
import com.admin.dto.TBaseUserDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TBaseUser;
import com.admin.service.TBaseUserService;
import com.admin.mapper.TBaseUserMapper;
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
* @description 针对表【t_base_user】的数据库操作Service实现
* @createDate 2022-06-13 22:44:19
*/
@Service
public class TBaseUserServiceImpl extends ServiceImpl<TBaseUserMapper, TBaseUser>
    implements TBaseUserService{

    @Resource
    private TBaseUserMapper tBaseUserMapper;
    @Override
    public IPage<TBaseUser> findUserlike(Page<TBaseUser> page, String name) {
        tBaseUserMapper.findRolelike(page,name);
        return page;

    }

    @Override
    public void deleteUserAll(@RequestParam(value = "ids") List<String> ids){
        List<Long> idsList = ids.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

        Iterator<Long> idsList1 = idsList.iterator();
        while (idsList1.hasNext()) {
            Long id = idsList1.next();
            tBaseUserMapper.deleteRoleByUser(id);
        }
    }

    @Override
    public Result addUser(TBaseUserDTO tBaseUserDTO){
        List<TBaseUser> UserByLoginName = tBaseUserMapper.findUserByLoginName(tBaseUserDTO.getLoginName());

        if (UserByLoginName.size() == 0) {
            TBaseUser tBaseUser=new TBaseUser();
            tBaseUserDTO.setStatus(0);
            BeanUtils.copyProperties(tBaseUserDTO, tBaseUser);
            save(tBaseUser);
            return Result.succ("成功添加用户");
        } else {

            return Result.succ("用户名已存在，请重新命名");
        }
    }

    @Override
    public Result updateUser( @RequestBody TBaseUserDTO tBaseUserDTO) {
        List<TBaseUser> userByName1 = tBaseUserMapper.findUserByLoginName(tBaseUserDTO.getLoginName());

        if (userByName1.size() == 0) {
            tBaseUserMapper.updateUser(tBaseUserDTO);
            return Result.succ("修改用户成功");
        } else {
            return Result.succ("用户名已存在，修改失败");
        }


    }
}




