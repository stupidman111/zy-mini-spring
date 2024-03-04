package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.BeanFactory;
import zyy.springframework.beans.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


  /**
   * 实现获取Bean的方法（方法中定义获取Bean的流程），具体获取Bean的流程定义为抽象方法交由子类实现
   * @param name
   * @return
   * @throws BeansException
   */
  @Override
  public Object getBean(String name) throws BeansException {
	return doGetBean(name, null);
  }

  @Override
  public Object getBean(String name, Object... args) throws BeansException {
	return doGetBean(name, args);
  }

  private <T> T doGetBean(final String name, final Object[] args) {
	Object bean = getSingleton(name);
	if (bean != null) {
	  return (T) bean;
	}

	BeanDefinition beanDefinition = getBeanDefinition(name);
	return (T) createBean(name, beanDefinition, args);
  }

  protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
