package zyy.springframework.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import zyy.springframework.beans.config.BeanDefinition;
import zyy.springframework.beans.BeanFactory;
import zyy.springframework.beans.support.DefaultListableBeanFactory;
import zyy.springframework.beans.support.SimpleInstantiationStrategy;
import zyy.springframework.test.bean.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ApiTest {

  /**
   * 使用beanFactory默认的Cglib实例化策略
   */
  @Test
  public void test_BeanFactory() {
	// 1.初始化 BeanFactory
	DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

	// 2. 注入bean
	BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
	beanFactory.registerBeanDefinition("userService", beanDefinition);

	// 3.获取bean
	UserService userService = (UserService) beanFactory.getBean("userService", "zyy");
	userService.queryUserInfo();
  }

  /**
   * 使用SimpleInstantiationStrategy实例化策略
   */
  @Test
  public void test_BeanFactory_with_SimpleInstantiationStrategy() {
	//1.初始化 BeanFactory
	DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

	//2.设置beanFactory的实例化策略为SimpleInstantiationStrategy
	beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

	//3.注入bean
	BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
	beanFactory.registerBeanDefinition("userService", beanDefinition);

	//4.获取bean
	UserService userService = (UserService) beanFactory.getBean("userService", "zyy");

	userService.queryUserInfo();
  }

  @Test
  public void test_newInstance() throws InstantiationException, IllegalAccessException {

	UserService userService = UserService.class.newInstance();//无参构造

	userService.queryUserInfo();
  }

  @Test
  public void test_getDeclaredConstructor() throws Exception {

	Class<UserService> userServiceClass = UserService.class;
	Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);//通过传入的参数类型，获取类的Class对象的构造方法
	UserService userService = declaredConstructor.newInstance("zyy");

	userService.queryUserInfo();
  }

  @Test
  public void test_getDeclaredConstructors() throws Exception {
	Class<UserService> userServiceClass = UserService.class;
	Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();

	Constructor<?> declaredConstructorWithNoParam = declaredConstructors[0];
	UserService userService = (UserService) declaredConstructorWithNoParam.newInstance();
	userService.queryUserInfo();

	Constructor<?> declaredConstructorWithStringParam = declaredConstructors[1];
	UserService userService1 = (UserService) declaredConstructorWithStringParam.newInstance("zyy");
	userService1.queryUserInfo();
  }

  /**
   * 获取构造器以后，还可以获取该构造器的参数列表
   * @throws Exception
   */
  @Test
  public void test_paramTypes() throws Exception {
	Class<UserService> beanClass = UserService.class;
	Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
	Constructor<?> constructor = declaredConstructors[1];

	Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
	UserService userService = declaredConstructor.newInstance("zyy");
	userService.queryUserInfo();
  }

  /**
   * 使用Cglib动态代理
   */
  @Test
  public void test_cglib() {
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(UserService.class);//设置需要代理的类
	enhancer.setCallback(new NoOp() {
	  @Override
	  public int hashCode() {
		return super.hashCode();
	  }
	});

	//通过被代理类的构造参数类型确定构造函数，并使用实参调用构造创建对象
	UserService userService = (UserService) enhancer.create(new Class[]{String.class}, new Object[]{"zyy"});
	userService.queryUserInfo();
  }
}
