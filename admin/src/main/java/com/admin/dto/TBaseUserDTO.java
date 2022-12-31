package com.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBaseUserDTO {

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private String createUser;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private String updateUser;

    /**
     *
     */
    private Integer version;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private String email;

    /**
     *
     */
    @TableField(value = "LOGIN_NAME")
    private String loginName;

    /**
     *
     */
    private String mobilePhone;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private String tel;

    private List<TBaseRoleDTO> tBaseRoleDTOS;

}

