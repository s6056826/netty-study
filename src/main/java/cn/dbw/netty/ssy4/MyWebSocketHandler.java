package cn.dbw.netty.ssy4;


import java.util.Date;
import java.util.Locale;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyWebSocketHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.println("�������յ�:"+msg.text());
		ctx.writeAndFlush("[������ʱ��]"+new Date());
	}

}
