package com.admin.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Data
@Repository
public class TBaseRoleDTO {
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
    private List<TBaseMenuDTO> tBaseMenuDTOS;

}
