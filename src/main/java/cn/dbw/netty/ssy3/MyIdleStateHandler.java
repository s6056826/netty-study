package cn.dbw.netty.ssy3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyIdleStateHandler  extends ChannelInboundHandlerAdapter{

	
	/**
	 * ��д�¼��ص��������ᴫ��evt�¼�����
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		//��Ϊ�����¼�
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent=(IdleStateEvent) evt;
			String eventType="";
			switch (idleStateEvent.state()) {
			case READER_IDLE:
				eventType="������";
				break;
            case WRITER_IDLE:
            	eventType="д����";
            	break;
            case ALL_IDLE:
            	eventType="��д����";
            	break;
			}
			System.out.println(eventType);
		}
	}
}
