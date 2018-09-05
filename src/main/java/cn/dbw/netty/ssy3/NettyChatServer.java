package cn.dbw.netty.ssy3;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * 长连接读写空闲检测机制
 * @author Administrator
 *
 */
public class NettyChatServer {

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
					//添加空闲检测事件机制
                    pipeline.addLast(new IdleStateHandler(6, 8,10,TimeUnit.SECONDS));
                    pipeline.addLast(new MyIdleStateHandler());
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
