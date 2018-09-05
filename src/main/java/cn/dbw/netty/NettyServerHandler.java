package cn.dbw.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler  extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("server channelRead..");
		System.out.println(ctx.channel().remoteAddress()+"->server:"+msg.toString());
		ctx.writeAndFlush("hi i'm netty server");
		
	}

	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception { 
		cause.printStackTrace();
		ctx.close();
	}
}
