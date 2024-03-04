package zyy.springframework.beans.support;

import zyy.springframework.beans.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  private final Map<String, Object> singletonObjects = new HashMap<>();//单例Bean对象池（final确保对应对象引用不可变性）


  @Override
  public Object getSingleton(String beanName) {
	return singletonObjects.get(beanName);
  }

  protected void addSingleton(String beanName, Object singletonObject) {
	singletonObjects.put(beanName, singletonObject);
  }
}
