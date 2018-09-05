package cn.dbw.netty.ssy2;

import java.nio.charset.Charset;

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
import io.netty.util.CharsetUtil;

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
					pipeline.addLast(new DelimiterBasedFrameDecoder(4096,Delimiters.lineDelimiter()));
					//Ä¬ÈÏStringDecoderÎª utf-8±à½âÂë
					pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
					pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
					pipeline.addLast(new MyChatServerHandler());
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
