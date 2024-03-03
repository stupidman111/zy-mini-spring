package zyy.springframework.test;

import org.junit.Test;
import zyy.springframework.beans.config.BeanDefinition;
import zyy.springframework.beans.BeanFactory;
import zyy.springframework.beans.support.DefaultListableBeanFactory;
import zyy.springframework.test.bean.UserService;

public class ApiTest {

  @Test
  public void test_BeanFactory() {
	//1.创建工厂
	DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

	//2.创建UserService的BeanDefinition信息
	BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);

	//3.向工厂中添加UserService的BeanDefinition信息
	beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

	//4.从工厂中获取UserService的一个单例对象
	UserService userService1 = (UserService) beanFactory.getBean("userService");

	//5.从工厂中获取UserService的一个单例对象（第二次获取）
	UserService userService2 = (UserService) beanFactory.getBean("userService");

	//6.比较这两个对象引用是否指向同一个对象（单例Bean工厂）
	if (userService1 == userService2) {
	  userService1.queryUserInfo();//查询用户信息
	} else {
	  userService1.queryUserInfo();
	  userService2.queryUserInfo();
	}
  }
}
