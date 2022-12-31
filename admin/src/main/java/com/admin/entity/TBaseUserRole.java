package com.admin.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName t_base_user_role
 */
@TableName(value ="t_base_user_role")
@Data
public class TBaseUserRole implements Serializable {
    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
