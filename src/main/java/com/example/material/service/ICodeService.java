
package com.example.material.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.material.entity.Code;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ICodeService extends IService<Code> {

	/**
	 * 提交
	 *
	 * @param code
	 * @return
	 */
	boolean submit(Code code);

}
