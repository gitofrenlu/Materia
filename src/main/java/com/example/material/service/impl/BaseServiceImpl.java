
package com.example.material.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;

import com.example.material.model.BaseEntity;
import com.example.material.model.BladeUser;
import com.example.material.secure.SecureUtil;
import com.example.material.service.BaseService;
import org.springframework.validation.annotation.Validated;

@Validated
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
	private Class<T> modelClass;

	public BaseServiceImpl() {
		Type type = this.getClass().getGenericSuperclass();
		this.modelClass = (Class)((ParameterizedType)type).getActualTypeArguments()[1];
	}

	public boolean save(T entity) {
		BladeUser user = SecureUtil.getUser();
		if (user != null) {
			entity.setCreateUser(user.getUserId());
			entity.setUpdateUser(user.getUserId());
		}

		LocalDateTime now = LocalDateTime.now();
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		if (entity.getStatus() == null) {
			entity.setStatus(1);
		}

		entity.setIsDeleted(0);
		return super.save(entity);
	}

	public boolean updateById(T entity) {
		BladeUser user = SecureUtil.getUser();
		if (user != null) {
			entity.setUpdateUser(user.getUserId());
		}

		entity.setUpdateTime(LocalDateTime.now());
		return super.updateById(entity);
	}

	public boolean deleteLogic(@NotEmpty List<Integer> ids) {
		return super.removeByIds(ids);
	}
}
