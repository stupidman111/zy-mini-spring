package zyy.springframework.beans;

import zyy.springframework.BeansException;

/**
 * 定义获取Bean的方法
 */
public interface BeanFactory {
  Object getBean(String name) throws BeansException;
}
