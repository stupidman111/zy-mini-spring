package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Bean实例化策略
 */
public interface InstantiationStrategy {

  Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;


}
