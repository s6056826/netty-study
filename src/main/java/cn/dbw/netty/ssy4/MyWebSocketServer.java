package cn.dbw.netty.ssy4;

import java.util.concurrent.TimeUnit;

import cn.dbw.netty.ssy3.MyIdleStateHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * netty µœ÷webscoket≥Ã–Ú
 * @author Administrator
 *
 */

public class MyWebSocketServer {
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGrop=new NioEventLoopGroup();
		EventLoopGroup workerGrop=new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap=new ServerBootstrap();
			bootstrap.group(bossGrop, workerGrop).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline(); 
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new HttpObjectAggregator(8192));
					pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
					pipeline.addLast(new MyWebSocketHandler());
				}
			});
			
			ChannelFuture channelFuture = bootstrap.bind(8809).sync();
			channelFuture.channel().closeFuture().sync();
			
		} finally {
			bossGrop.shutdownGracefully();
			workerGrop.shutdownGracefully();			
		}
	
	}

}
