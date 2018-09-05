package cn.dbw.netty.ssy2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{

	private static final ChannelGroup channelGrop=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
     	 Channel channel = ctx.channel();
		 System.out.println("�ѷ��ͣ�"+msg);
		 for(Channel ch:channelGrop) {
			 if(ch!=channel) {
				 ch.writeAndFlush("�ͻ��ˣ�"+channel.remoteAddress()+" send:"+msg+"\n");
			 }else {
				 ch.writeAndFlush("[me] send:"+msg+"\n");
			 }
		 }
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		 Channel channel = ctx.channel();
		 channelGrop.writeAndFlush("�ͻ���"+channel.remoteAddress()+" ����\n");
         channelGrop.add(channel);
         System.out.println("��ǰ�����û�����"+channelGrop.size());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
	     channelGrop.writeAndFlush("�ͻ���"+ctx.channel().remoteAddress()+" ����\n");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	

}
