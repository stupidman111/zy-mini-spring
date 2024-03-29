package zyy.springframework.beans.support;

import zyy.springframework.BeansException;
import zyy.springframework.beans.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry{

  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
  @Override
  protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
	BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
	if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "is defined");
	return beanDefinition;
  }

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
	beanDefinitionMap.put(beanName, beanDefinition);
  }

}
