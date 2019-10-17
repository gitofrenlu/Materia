package com.example.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.material.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 方式1：使用注解编写SQL。
	 */
	@Select("select * from blade_user where account = #{account} and password = #{password}  and is_deleted = 0")
	User get(@Param("account") String account, @Param("password") String password);

	//@Select("SELECT  role_alias  FROM   blade_role WHERE  id = #{id} and is_deleted = 0")
	//List<String> getRoleAlias(@Param("id") String id);

	List<String> getRoleAlias(String[] ids);

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param user
	 * @return
	 */
	List<User> selectUserPage(IPage page, User user);

	/**
	 * 获取用户
	 *
	 * @param tenantId
	 * @param account
	 * @param password
	 * @return
	 */
	User getUser(String tenantId, String account, String password);

	/**
	 * 获取角色名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleName(String[] ids);


	/**
	 * 获取部门名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getDeptName(String[] ids);

}
