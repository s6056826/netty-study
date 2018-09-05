package cn.dbw.netty.ssy7;

public class Decorator implements Component {

	private volatile Component component;
	
	
	public Decorator(Component component) {
		this.component=component;
	}
	
	public void doSomething() {
		component.doSomething();
	}
	
	

}
