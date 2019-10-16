
package com.example.material.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_tenant")
@EqualsAndHashCode(callSuper = true)
//@ApiModel(value = "Tenant对象", description = "Tenant对象")
public class Tenant extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	//@ApiModelProperty(value = "主键id")
	private Integer id;

	/**
	 * 租户ID
	 */
	//@ApiModelProperty(value = "租户ID")
	private String tenantId;
	/**
	 * 租户名称
	 */
	//@ApiModelProperty(value = "租户名称")
	private String tenantName;
	/**
	 * 联系人
	 */
	//@ApiModelProperty(value = "联系人")
	private String linkman;
	/**
	 * 联系电话
	 */
	//@ApiModelProperty(value = "联系电话")
	private String contactNumber;
	/**
	 * 联系地址
	 */
	//@ApiModelProperty(value = "联系地址")
	private String address;


}
