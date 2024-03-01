package zyy.springframework.test.ioc;


import org.junit.Test;
import zyy.springframework.beans.factory.BeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBeanContainerTest {

  @Test
  public void testGetBean() {
	BeanFactory beanFactory = new BeanFactory();

	//存储一个bean对象
	beanFactory.registerBean("helloService", new HelloService());

	//获取该bean对象
	HelloService helloService = (HelloService) beanFactory.getBean("helloService");
	assertThat(helloService).isNotNull();
	assertThat(helloService.sayHello()).isEqualTo("hello");
  }

  class HelloService {
	public String sayHello() {
	  System.out.println("hello");
	  return "hello";
	}
  }
}
