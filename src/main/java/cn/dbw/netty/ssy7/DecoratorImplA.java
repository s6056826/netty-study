package cn.dbw.netty.ssy7;

public class DecoratorImplA extends Decorator {

	public DecoratorImplA(Component component) {
		super(component);
	}
    
	@Override
	public void doSomething() {
		super.doSomething();
		System.out.println("¹¦ÄÜB");
	}
}
