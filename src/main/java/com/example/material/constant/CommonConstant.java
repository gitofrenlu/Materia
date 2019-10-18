package com.example.material.constant;


/**
 * 通用常量
 *
 * @author Chill
 */
public interface CommonConstant {


	/**
	 * saber 系统名
	 */
	String SABER_NAME = "saber";

	/**
	 * 顶级父节点id
	 */
	Integer TOP_PARENT_ID = 0;

	/**
	 * 顶级父节点名称
	 */
	String TOP_PARENT_NAME = "顶级";


	/**
	 * 默认密码
	 */
	String DEFAULT_PASSWORD = "123456";

	String ADMIN_TENANT_ID = "000000";


	String API_PREFIX = "/api/";

	int DB_NOT_DELETED = 0;

	public static final String ADMIN = "administrator";
	public static final String HAS_ROLE_ADMIN = "hasRole('administrator')";
	public static final String USER = "user";
	public static final String HAS_ROLE_USER = "hasRole('user')";
	public static final String TEST = "test";
	public static final String HAS_ROLE_TEST = "hasRole('test')";

}
