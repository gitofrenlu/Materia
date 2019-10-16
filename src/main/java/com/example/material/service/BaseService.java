package com.example.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import javax.validation.constraints.NotEmpty;

public interface BaseService<T> extends IService<T> {
	boolean deleteLogic(@NotEmpty List<Integer> ids);
}
