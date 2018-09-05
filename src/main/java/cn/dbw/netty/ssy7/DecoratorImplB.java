package cn.dbw.netty.ssy7;

public class DecoratorImplB extends Decorator {

	public DecoratorImplB(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doSomething() {
		super.doSomething();	
		System.out.println("¹¦ÄÜC");
	}

}
