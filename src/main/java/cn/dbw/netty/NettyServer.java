package cn.dbw.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
	 private int port;
	    
	    public NettyServer(int port) {
	        this.port = port;
	    }
	    
	    public void start(){
	        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap sbs = new ServerBootstrap().group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
	                    .childHandler(new ChannelInitializer<SocketChannel>() {
	                        
	                        protected void initChannel(SocketChannel ch) throws Exception {
//	                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
	                            ch.pipeline().addLast("decoder", new StringDecoder());
	                            ch.pipeline().addLast("encoder", new StringEncoder());
	                            ch.pipeline().addLast(new NettyServerHandler());
	                        };
	                        
	                    }).option(ChannelOption.SO_BACKLOG, 128)   
	                    .childOption(ChannelOption.SO_KEEPALIVE, true);
	             // �󶨶˿ڣ���ʼ���ս���������
	             ChannelFuture future = sbs.bind(port).sync();  
	             
	             System.out.println("Server start listen at " + port );
	             future.channel().closeFuture().sync();
	        } catch (Exception e) {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }
	    
	    public static void main(String[] args) {
			new NettyServer(8883).start();
		}

}