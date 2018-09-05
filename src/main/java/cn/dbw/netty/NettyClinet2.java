package cn.dbw.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClinet2 {
	
	private final static Channel CHANNEL=new NioSocketChannel();
	public void connect(){
		Channel channel=CHANNEL;
		final ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1", 8883));
		channelFuture.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					ByteBuf buf=Unpooled.copiedBuffer("hello", Charset.defaultCharset());
				    channelFuture.channel().writeAndFlush("clinet to ...");
				}else{
					Throwable cause = future.cause();
					cause.printStackTrace();
				}
			}
		});
		
	}
	
	public static void main(String[] args) {
		new NettyClinet2().connect();
	}

}
