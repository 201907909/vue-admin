package com.admin.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName t_base_role_menu
 */
@TableName(value ="t_base_role_menu")
@Data
public class TBaseRoleMenu implements Serializable {
    /**
     *
     */
    private Long roleId;

    /**
     *
     */
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
