package cn.dbw.netty.ssy6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.dbw.netty.ssy2.MyChatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyProtobufClinet {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
	     // Configure the client.
       EventLoopGroup group = new NioEventLoopGroup();
       try {
           Bootstrap b = new Bootstrap();
           b.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
           		@Override
   				protected void initChannel(SocketChannel ch) throws Exception {
   					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new ProtobufVarint32FrameDecoder());
					pipeline.addLast(new ProtobufDecoder(MultiData.MyMessage.getDefaultInstance()));
					pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
					pipeline.addLast(new ProtobufEncoder());
					pipeline.addLast(new MyProtobufClientHandler());
   				}
            });
           Channel channel = b.connect("127.0.0.1", 8809).sync().channel();
           channel.closeFuture().sync();
	 }finally {
		group.shutdownGracefully();
	 }
	}

}
