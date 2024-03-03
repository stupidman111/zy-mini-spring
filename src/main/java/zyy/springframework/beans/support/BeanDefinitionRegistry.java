package zyy.springframework.beans.support;

import zyy.springframework.beans.config.BeanDefinition;

public interface BeanDefinitionRegistry {

  void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
