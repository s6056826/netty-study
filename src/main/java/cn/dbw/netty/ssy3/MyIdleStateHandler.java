package cn.dbw.netty.ssy3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyIdleStateHandler  extends ChannelInboundHandlerAdapter{

	
	/**
	 * 重写事件回调函数，会传入evt事件参数
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		//若为空闲事件
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent=(IdleStateEvent) evt;
			String eventType="";
			switch (idleStateEvent.state()) {
			case READER_IDLE:
				eventType="读空闲";
				break;
            case WRITER_IDLE:
            	eventType="写空闲";
            	break;
            case ALL_IDLE:
            	eventType="读写空闲";
            	break;
			}
			System.out.println(eventType);
		}
	}
}
