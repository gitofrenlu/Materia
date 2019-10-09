package com.example.material.mapper;

import com.example.material.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

	/**
	 * 方式1：使用注解编写SQL。
	 */
	@Select("select * from blade_user where account = #{account} and password = #{password}  and is_deleted = 0")
	User get(@Param("account") String account, @Param("password") String password);

	@Select("SELECT  role_alias  FROM   blade_role WHERE  id = #{id} and is_deleted = 0")
	List<String> getRoleAlias(@Param("id") String id);
}
