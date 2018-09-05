package cn.dbw.netty.ssy2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyChatClient {

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
    					pipeline.addLast(new DelimiterBasedFrameDecoder(4096,Delimiters.lineDelimiter()));
    					//Ä¬ÈÏStringDecoderÎª utf-8±à½âÂë
    					pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
    					pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
    					pipeline.addLast(new MyChatClientHandler());
    				}
             });
            Channel channel = b.connect("127.0.0.1", 8809).sync().channel();
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            for(;;) {
            	channel.writeAndFlush(reader.readLine()+"\r\n");
            }
	 }finally {
		group.shutdownGracefully();
	 }
	}
}
