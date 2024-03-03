package zyy.springframework.beans.config;

/**
 * 定义获取单例Bean的方法
 */
public interface SingletonBeanRegistry {
  Object getSingleton(String beanName);
}
