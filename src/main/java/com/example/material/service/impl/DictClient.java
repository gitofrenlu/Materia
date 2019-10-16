
package com.example.material.service.impl;


import com.example.material.entity.Dict;
import com.example.material.service.IDictClient;
import com.example.material.service.IDictService;
import com.example.material.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;


/**
 * 字典服务Feign实现类
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@Service
public class DictClient implements IDictClient {


	IDictService service;

	@Override
	@GetMapping("/dict/getValue")
	public R<String> getValue(String code, Integer dictKey) {
		return R.data(service.getValue(code, dictKey));
	}

	@Override
	@GetMapping("/dict/getList")
	public R<List<Dict>> getList(String code) {
		return R.data(service.getList(code));
	}

}
