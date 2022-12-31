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
 * @TableName t_base_menu
 */
@TableName(value ="t_base_menu")
@Data
public class TBaseMenu implements Serializable {
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
    private String ico;

    /**
     * 
     */
    private String markUrl;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer orderNo;

    /**
     * 
     */
    private Integer type;



    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Long parentId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String title;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}