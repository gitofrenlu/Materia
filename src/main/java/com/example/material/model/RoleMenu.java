
package com.example.material.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_role_menu")
//@ApiModel(value = "RoleMenu对象", description = "RoleMenu对象")
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	//@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 菜单id
	 */
	//@ApiModelProperty(value = "菜单id")
	private Integer menuId;

	/**
	 * 角色id
	 */
	//@ApiModelProperty(value = "角色id")
	private Integer roleId;


}
