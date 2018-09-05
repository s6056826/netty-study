package cn.dbw.netty.ssy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestHttpServer {
	
	public static void main(String[] args) {
		EventLoopGroup boosGroop=new NioEventLoopGroup();
		EventLoopGroup workerGroop=new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap=new ServerBootstrap();
			bootstrap.group(boosGroop,workerGroop).channel(NioServerSocketChannel.class)
			.childHandler(new TestServerInitHandler());
			
			ChannelFuture channelFuture = bootstrap.bind(8089).sync();
			channelFuture.channel().closeFuture().sync();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			workerGroop.shutdownGracefully();
			boosGroop.shutdownGracefully();
		}
		
	}

}
