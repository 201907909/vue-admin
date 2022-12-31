package com.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TBaseUserRoleDTO {
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
