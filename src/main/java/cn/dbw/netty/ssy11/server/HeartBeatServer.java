package cn.dbw.netty.ssy11.server;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartBeatServer {
	
	private int port=0;

    private final AcceptorIdleStateTrigger idleStateTrigger = new AcceptorIdleStateTrigger();
	
	
	
	public HeartBeatServer(int port) {
		this.port = port;
	}

	public void start(){
		EventLoopGroup workerGroup=new NioEventLoopGroup(1);
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap=new ServerBootstrap();
			serverBootstrap.group(workerGroup, bossGroup).channel(NioServerSocketChannel.class)
			   .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					 ChannelPipeline pipeline = ch.pipeline();
					 //添加一个读写空闲事件处理器
					 pipeline.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
					 pipeline.addLast(idleStateTrigger);
					 pipeline.addLast(new StringDecoder());
					 pipeline.addLast(new StringEncoder());
					 pipeline.addLast(new HeartBeatServerHandler());
					
				}
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture channelFuture = serverBootstrap.bind(this.port).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
		    bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		int port=9999;
		//启动服务器
		new HeartBeatServer(port).start();
	}

}
