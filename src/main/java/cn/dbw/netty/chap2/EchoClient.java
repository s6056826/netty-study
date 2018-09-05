package cn.dbw.netty.chap2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClient {
	
	private final String host;
	private final int port;
	
	public EchoClient(String host,int port) {
		this.host=host;
		this.port=port;
	}
    
	public void connect() throws InterruptedException{
		EventLoopGroup group=new NioEventLoopGroup();
		try {
		   	Bootstrap bootstrap=new Bootstrap();
		   	bootstrap.group(group)
		   	.channel(NioSocketChannel.class)
		   	.remoteAddress(new InetSocketAddress(host, port))
		   	.handler(new ChannelInitializer<SocketChannel>() {
		   	@Override
		   	public void initChannel(SocketChannel ch)
		   	throws Exception {
		   	ch.pipeline().addLast(
		   	new EchoClientHandler());
		   	}
		   	});
		   	ChannelFuture f = bootstrap.connect().sync();
		   	f.channel().writeAndFlush(Unpooled.copiedBuffer("hahhahahaa",CharsetUtil.UTF_8));
		   	f.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new EchoClient("127.0.0.1", 8883).connect();
	}
}
