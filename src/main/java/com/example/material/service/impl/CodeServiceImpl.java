
package com.example.material.service.impl;


import com.example.material.constant.CommonConstant;
import com.example.material.entity.Code;
import com.example.material.mapper.CodeMapper;
import com.example.material.service.ICodeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements ICodeService {

	@Override
	public boolean submit(Code code) {
		code.setIsDeleted(CommonConstant.DB_NOT_DELETED);
		return saveOrUpdate(code);
	}

}
