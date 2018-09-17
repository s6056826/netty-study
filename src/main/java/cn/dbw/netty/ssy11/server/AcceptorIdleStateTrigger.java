package cn.dbw.netty.ssy11.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		 //判断是否是空闲事件
		 if(evt instanceof IdleStateEvent){
			 IdleState idleState = ((IdleStateEvent) evt).state();
			 switch (idleState) {
		       case READER_IDLE:
		    	 //在此抛出一个异常，很有意思，将会被下游的handler的捕获异常的处理器捕获到，然后断开客户端连接
				 throw new Exception("idle exception"); 
			}
		 }else{
			 super.userEventTriggered(ctx, evt);
		 }
	}

}
