package com.example.material.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.Converter;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanUtil extends BeanUtils {
	public BeanUtil() {
	}

	public static <T> T newInstance(Class<T> clazz) {

		return instantiateClass(clazz);
	}

	public static <T> T newInstance(String clazzStr) {
		try {
			Class<T> clazz = (Class<T>) Class.forName(clazzStr);
			return newInstance(clazz);
		} catch (ClassNotFoundException var2) {
			throw new RuntimeException(var2);
		}
	}

	public static Object getProperty(Object bean, String propertyName) {
		Assert.notNull(bean, "bean Could not null");
		return BeanMap.create(bean).get(propertyName);
	}

	public static void setProperty(Object bean, String propertyName, Object value) {
		Assert.notNull(bean, "bean Could not null");
		BeanMap.create(bean).put(propertyName, value);
	}

	//public static <T> T clone(T source) {
	//	return copy(source, source.getClass());
	//}

	public static <T> T copy(Object source, Class<T> clazz) {
		BaseBeanCopier copier = BaseBeanCopier.create(source.getClass(), clazz, false);
		T to = newInstance(clazz);
		copier.copy(source, to, (Converter)null);
		return to;
	}

	public static void copy(Object source, Object targetBean) {
		BaseBeanCopier copier = BaseBeanCopier.create(source.getClass(), targetBean.getClass(), false);
		copier.copy(source, targetBean, (Converter)null);
	}

	public static <T> T copyProperties(Object source, Class<T> target) throws BeansException {
		T to = newInstance(target);
		copyProperties(source, to);
		return to;
	}

	public static Map<String, Object> toMap(Object bean) {
		return BeanMap.create(bean);
	}

	public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
		T bean = newInstance(valueType);
		BeanMap.create(bean).putAll(beanMap);
		return bean;
	}

	public static Object generator(Object superBean, BeanProperty... props) {
		Class<?> superclass = superBean.getClass();
		Object genBean = generator(superclass, props);
		copy(superBean, genBean);
		return genBean;
	}

	public static Object generator(Class<?> superclass, BeanProperty... props) {
		BeanGenerator generator = new BeanGenerator();
		generator.setSuperclass(superclass);
		generator.setUseCache(true);
		BeanProperty[] var3 = props;
		int var4 = props.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			BeanProperty prop = var3[var5];
			generator.addProperty(prop.getName(), prop.getType());
		}

		return generator.create();
	}

	public static PropertyDescriptor[] getBeanGetters(Class type) {
		return getPropertiesHelper(type, true, false);
	}

	public static PropertyDescriptor[] getBeanSetters(Class type) {
		return getPropertiesHelper(type, false, true);
	}

	private static PropertyDescriptor[] getPropertiesHelper(Class type, boolean read, boolean write) {
		try {
			PropertyDescriptor[] all = getPropertyDescriptors(type);
			if (read && write) {
				return all;
			} else {
				List<PropertyDescriptor> properties = new ArrayList(all.length);
				PropertyDescriptor[] var5 = all;
				int var6 = all.length;

				for(int var7 = 0; var7 < var6; ++var7) {
					PropertyDescriptor pd = var5[var7];
					if (read && pd.getReadMethod() != null) {
						properties.add(pd);
					} else if (write && pd.getWriteMethod() != null) {
						properties.add(pd);
					}
				}

				return (PropertyDescriptor[])properties.toArray(new PropertyDescriptor[0]);
			}
		} catch (BeansException var9) {
			throw new CodeGenerationException(var9);
		}
	}
}
