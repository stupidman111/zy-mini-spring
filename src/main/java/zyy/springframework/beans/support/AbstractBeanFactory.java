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

	Object bean = getSingleton(name);//获取单例bean

	if (bean != null) {
	  return bean;
	}

	//1. 流程一：获取名为name的Bean的BeanDefinition
	BeanDefinition beanDefinition = getBeanDefinition(name);
	//2. 流程二：通过Bean的name和BeanDefinition 向Bean单例池中注册一个Bean
	return createBean(name, beanDefinition);
  }

  protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
