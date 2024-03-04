package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * 简单实例化策略
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
  @Override
  public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
	Class clazz = beanDefinition.getBeanClass();
	try {
	  if (null != ctor) {//构造不为空---有构造函数实例化
		return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
	  } else {//ctor为空---无构造函数实例化
		return clazz.getDeclaredConstructor().newInstance();
	  }
	}  catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
	  throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
	}
  }
}
