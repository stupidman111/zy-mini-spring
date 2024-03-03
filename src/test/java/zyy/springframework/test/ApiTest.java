package zyy.springframework.test;

import org.junit.Test;
import zyy.springframework.BeanDefinition;
import zyy.springframework.BeanFactory;
import zyy.springframework.test.bean.UserService;

public class ApiTest {

  @Test
  public void test_BeanFactory() {
	//1. Bean工厂
	BeanFactory beanFactory = new BeanFactory();

	//2. 注入Bean
	BeanDefinition beanDefinition = new BeanDefinition(new UserService());
	beanFactory.registerBeanDefinition("userService", beanDefinition);

	//3. 获取Bean
	UserService userService = (UserService) beanFactory.getBean("userService");
	userService.queryUserInfo();
  }
}
