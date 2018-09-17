package cn.dbw.netty.ssy11.client;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

public class HeartBeatsClient {
	//延时任务处理器实现类
	private final HashedWheelTimer timer=new HashedWheelTimer();
	
	private final ConnectorIdleStateTrigger idleStateTrigger=new ConnectorIdleStateTrigger();
	
	private Bootstrap bootstrap;
	
	
	
	public void connect(String host,int port) throws InterruptedException{
		EventLoopGroup wsGroup=new NioEventLoopGroup();
		bootstrap=new Bootstrap();
		bootstrap.group(wsGroup).handler(new LoggingHandler(LogLevel.INFO)).channel(NioSocketChannel.class);
		final ConnectWatchingDog connectWatchingDog=new ConnectWatchingDog(bootstrap,timer,host,port,true,12) {
			public ChannelHandler[] handlers() {
				// TODO Auto-generated method stub
				return new ChannelHandler[]{
						this,
						new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS),
						idleStateTrigger,
						new StringDecoder(),
						new StringEncoder(),
						new HeartBeatClientHandler()
				};
			}
		};
		
		ChannelFuture channelFuture;
		
		synchronized (bootstrap) {
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(connectWatchingDog.handlers());
				}
			});
			channelFuture=bootstrap.connect(host, port);
		}
		
	    channelFuture.sync();	   
	}
     
	public static void main(String[] args) {
		try {
			new HeartBeatsClient().connect("localhost", 9999);
		} catch (Exception e) {
			System.out.println("连接失败");
		}
		
	}
}
