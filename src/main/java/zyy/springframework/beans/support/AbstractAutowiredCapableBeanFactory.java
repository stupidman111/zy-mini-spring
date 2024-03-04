package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory{

  //默认使用Cglib实例化策略
  private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
	Object bean = null;
	try {
	  bean = createBeanInstance(beanDefinition, beanName, args);
	} catch (Exception e) {
	  throw new BeansException("Instantiation of bean failed", e);
	}

	addSingleton(beanName, bean);
	return bean;
  }

  protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
	Constructor constructorToUse = null;
	Class<?> beanClass = beanDefinition.getBeanClass();
	Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

	for (Constructor<?> ctor : declaredConstructors) {//这里粗暴对比参数个数相同就锁定该构造，实际还需要对比参数类型等。
	  if (null != args && ctor.getParameterTypes().length == args.length) {
		constructorToUse = ctor;
		break;
	  }
	}

	return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
  }

  public InstantiationStrategy getInstantiationStrategy() {
	return instantiationStrategy;
  }

  public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
	this.instantiationStrategy = instantiationStrategy;
  }
}
