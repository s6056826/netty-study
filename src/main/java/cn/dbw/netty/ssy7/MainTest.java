package cn.dbw.netty.ssy7;

public class MainTest {
	
	
	
	public static void main(String[] args) {
		Component component=new DecoratorImplB(new DecoratorImplA(new ConcurrentComponent()));
		component.doSomething();
	}

}
