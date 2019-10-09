
package com.example.material.model;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
public class User {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 真名
	 */
	private String realName;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 生日
	 */
	private LocalDateTime birthday;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 部门id
	 */
	private String deptId;


}
