package cn.dbw.netty.ssy6;

import java.util.concurrent.TimeUnit;

import cn.dbw.netty.ssy3.MyIdleStateHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyProtoBufServer {
	
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
					pipeline.addLast(new ProtobufVarint32FrameDecoder());
					pipeline.addLast(new ProtobufDecoder(MultiData.MyMessage.getDefaultInstance()));
					pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
					pipeline.addLast(new ProtobufEncoder());
					pipeline.addLast(new MyProtoBufHandler());
					
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
