
package com.example.material.service;


import com.example.material.model.Dict;
import com.example.material.utils.R;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Feign接口类
 *
 * @author Chill
 */
public interface IDictClient {


	/**
	 * 获取字典表对应值
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	R<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") Integer dictKey);

	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	R<List<Dict>> getList(@RequestParam("code") String code);

}
