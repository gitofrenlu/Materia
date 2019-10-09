package com.example.material.secure;

import com.example.material.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ClientDetailsServiceImpl implements IClientDetailsService {

	@Resource
	private ClientMapper clientMapper;


	public IClientDetails loadClientByClientId(String clientId) {
		return clientMapper.get(clientId);

	}

}

