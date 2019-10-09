package com.example.material.mapper;

import com.example.material.secure.ClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper {

	@Select("select client_id, client_secret, access_token_validity, refresh_token_validity from blade_client where client_id = #{clientId}")
	ClientDetails get(@Param("clientId")String clientId);
}
