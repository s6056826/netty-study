package cn.dbw.netty.ssy11.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		 //�ж��Ƿ��ǿ����¼�
		 if(evt instanceof IdleStateEvent){
			 IdleState idleState = ((IdleStateEvent) evt).state();
			 switch (idleState) {
		       case READER_IDLE:
		    	 //�ڴ��׳�һ���쳣��������˼�����ᱻ���ε�handler�Ĳ����쳣�Ĵ��������񵽣�Ȼ��Ͽ��ͻ�������
				 throw new Exception("idle exception"); 
			}
		 }else{
			 super.userEventTriggered(ctx, evt);
		 }
	}

}
