package com.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 *
 * @TableName t_base_user
 */
@TableName(value ="t_base_user")
@Data
public class TBaseUser implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
