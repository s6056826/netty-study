package cn.dbw.netty.chap2;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoServerChannelHandler extends ChannelInboundHandlerAdapter {
    
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(ctx.channel().remoteAddress()+"  connect was build");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    ByteBuf byteBuf=(ByteBuf) msg;
        System.out.println("sever received:"+byteBuf.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(byteBuf);
 	}
    /**
         * �������������ChannelPipeline���д������ʱ������
     */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	/**
	 * ����ChannelHandler��ӵ�ChannelPipeline��ʱ������
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}
	/**
	 * ����ChannelPipeline���Ƴ�ChannelHandlerʱ������
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerRemoved(ctx);
	}
}
