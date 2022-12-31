package com.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 *
 * @TableName t_base_role
 */
@TableName(value ="t_base_role")
@Data
public class TBaseRole implements Serializable {
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
    private String description;

    /**
     *
     */
    private String name;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
