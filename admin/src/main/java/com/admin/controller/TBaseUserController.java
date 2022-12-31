package com.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.admin.dto.LoginDTO;
import com.admin.common.lang.Result;
import com.admin.dto.TBaseUserDTO;
import com.admin.entity.TBaseUser;
import com.admin.entity.TBaseUserRole;
import com.admin.mapper.TBaseUserMapper;
import com.admin.mapper.TBaseUserRoleMapper;
import com.admin.service.TBaseUserRoleService;
import com.admin.service.TBaseUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TBaseUserController {
    @Resource
    TBaseUserService tBaseUserService;

    @Resource
    TBaseUserRoleService tBaseUserRoleService;

    @Resource
    TBaseUserMapper tBaseUserMapper;

    @Resource
    TBaseUserRoleMapper tBaseUserRoleMapper;

        /**
         * 登录
         * @param loginDto
         * @param session
         * @return
         */
        @PostMapping("/login")
        public Result Login(@Validated @RequestBody LoginDTO loginDto , HttpSession session) {
            TBaseUser tBaseUser = tBaseUserService.getOne(new QueryWrapper<TBaseUser>().eq("login_name", loginDto.getLoginName()));
            if (tBaseUser==null) {
                return Result.fail("用户不存在");
            }
            if (!(tBaseUser.getPassword().equals(loginDto.getPassword()))) {
                return Result.fail("密码不正确");

            }
            Long id = tBaseUser.getId();
            tBaseUser.setStatus(1);
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("id",id);
            tBaseUserService.update(tBaseUser,wrapper);
            session.setAttribute("session",tBaseUser);
            //格式化时间
            String time = DateUtil.format(tBaseUser.getUpdateTime(),"yyyy-MM-dd hh:mm:ss");

            return Result.succ(MapUtil.builder()
                    .put("id", tBaseUser.getId())
                    .put("loginName", tBaseUser.getLoginName())
                    .put("name",tBaseUser.getName())
                    .put("sex",tBaseUser.getSex())
                    .put("address", tBaseUser.getAddress())
                    .put("updateTime", time)
                    .put("email",tBaseUser.getEmail())
                    .put("mobilePhone",tBaseUser.getMobilePhone())
                    .map());
        }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
        public Result logout(@RequestParam(value = "id") long id, HttpSession session) {
        TBaseUser user = tBaseUserService.getById(id);
        user.setStatus(0);
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id",id);
        tBaseUserService.update(user,wrapper);


        session.invalidate();
            return Result.succ(true);
        }





    /**
     *登录用户信息的编辑
     * @param id
     * @return
     */
    @GetMapping("/getUser")
    public Result getUser(Long id) {
        TBaseUser user = tBaseUserService.getById(id);
        //格式化时间
        String time = DateUtil.format(user.getUpdateTime(),"yyyy-MM-dd hh:mm:ss");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("loginName", user.getLoginName())
                .put("name",user.getName())
                .put("sex",user.getSex())
                .put("address", user.getAddress())
                .put("updateTime", time)
                .put("email",user.getEmail())
                .put("mobilePhone",user.getMobilePhone())
                .map());
    }

    /**
     * 更新用户
     * @param baseUser
     * @return
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody TBaseUser baseUser) {
        TBaseUser temp = new TBaseUser();
        temp.setUpdateTime(new Date());
        BeanUtil.copyProperties(baseUser,temp,"id","updateTime");
        UpdateWrapper<TBaseUser> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",baseUser.getId());
        tBaseUserMapper.update(temp, wrapper);
        String time = DateUtil.format(temp.getUpdateTime(),"yyyy-MM-dd hh:mm:ss");
        return Result.succ(MapUtil.builder()
                .put("id", temp.getId())
                .put("loginName", temp.getLoginName())
                .put("name",temp.getName())
                .put("sex",temp.getSex())
                .put("address", temp.getAddress())
                .put("updateTime", time)
                .put("email",temp.getEmail())
                .put("mobilePhone",temp.getMobilePhone())
                .map());

    }


    /**
     * 查询用户
     * @param currentPage
     * @return
     * @throws Exception
     */

    @GetMapping("/getUsers")
    public Result users(@RequestParam(defaultValue = "1") Integer currentPage) throws Exception{

        Page page = new Page(currentPage, 5);
        IPage<TBaseUser> pageData = tBaseUserService.page(page, new QueryWrapper<TBaseUser>().orderByAsc("ID"));

        return Result.succ(pageData);
    }

    /**
     *
     * @param name
     * @param currentPage
     * @return
     */

       @GetMapping("finduserlike")
    public Result findlike(@RequestParam(value = "name") String name,@RequestParam(defaultValue = "1") Integer currentPage){
           Page page = new Page(currentPage, 2);
        IPage<TBaseUser> tBaseUserIPage=tBaseUserService.findUserlike(page,name);
        return Result.succ(tBaseUserIPage);
    }

    /**
     * 为用户增加角色
     * @param userId
     * @param roleids
     * @return
     */
    @DeleteMapping("/adduserole")
    public Result add(Long userId, @RequestParam(value = "roleids") List<Long> roleids){

        Result result = tBaseUserRoleService.adduserrole(userId, roleids);
        return result;
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam(value = "ids")List<String> ids){
        tBaseUserService.deleteUserAll(ids);
        tBaseUserService.removeByIds(ids);
        return  Result.succ("删除成功");
    }

    /**
     *新增用户
     * @param
     * @return
     */
    @PostMapping("/addNewUser")
    public Result addUser(@RequestBody TBaseUserDTO tBaseUserDTO){

        return tBaseUserService.addUser(tBaseUserDTO);
    }

    /**
     * 用户信息及用户的角色
     * @param id
     * @return
     */

    @PostMapping("/find")
    public Result find(@RequestParam(value = "id") long id){

        return Result.succ(tBaseUserMapper.selectRoleByUserId1(id));

    }




    /**
     * id查找用户
     * @param id
     * @return
     */
    @GetMapping("/findIdUser")
    public Result findIdUser(@RequestParam(value = "id")long id){


        return Result.succ(tBaseUserMapper.selectById(id));
    }
    /**
     * 更新用户
     * @param tBaseUserDTO
     * @return
     */
    @PostMapping("/update")
    public Result updateUser(@RequestBody TBaseUserDTO tBaseUserDTO){

        return tBaseUserService.updateUser(tBaseUserDTO);
    }




}



