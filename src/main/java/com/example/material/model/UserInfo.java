
package com.example.material.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 用户信息
 *
 * @author Chill
 */
@Data
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户基础信息
	 *
	 * 用户
	 */
	private User user;

	/**
	 * 权限标识集合
	 *
	 * 权限集合
	 */
	private List<String> permissions;

	/**
	 * 角色集合
	 */
	private List<String> roles;

}
