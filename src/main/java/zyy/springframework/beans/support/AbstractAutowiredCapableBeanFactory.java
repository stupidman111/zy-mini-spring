package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory{
  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
	Object bean;
	try {
	  bean = beanDefinition.getBeanClass().newInstance();
	} catch (InstantiationException | IllegalAccessException e) {
	  throw new BeansException("Instantiation of bean failed", e);
	}

	addSingleton(beanName, bean);
	return bean;
  }
}
