package zyy.springframework.beans.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Cglib实例化策略
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
  @Override
  public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(beanDefinition.getBeanClass());
	enhancer.setCallback(new NoOp() {
	  @Override
	  public int hashCode() {
		return super.hashCode();
	  }
	});

	if (null == ctor) return enhancer.create();
	return enhancer.create(ctor.getParameterTypes(), args);

  }
}
