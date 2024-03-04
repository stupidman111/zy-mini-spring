package zyy.springframework.beans;

import zyy.springframework.BeansException;

/**
 * 定义获取Bean的方法
 */
public interface BeanFactory {
  //只通过名字获取bean--底层调用无参构造
  Object getBean(String name) throws BeansException;

  //通过名字 + 参数获取bean--底层调用带参构造
  Object getBean(String name, Object... args) throws BeansException;
}
