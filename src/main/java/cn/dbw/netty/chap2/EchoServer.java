package cn.dbw.netty.chap2;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class EchoServer {
       private final int port;
       
       public EchoServer(int port) {
		this.port=port;
    }
       
       public void start() throws InterruptedException{
    	   //NioEventLoopGroup 为nio非阻塞事件驱动
    	 //EpollEventLoopGroup eventLoopGroup=new EpollEventLoopGroup(); epoll异步高性能非阻塞io
    	  //与EpollServerSocketChannel对应
    	   /*
    	    *  OioEventLoopGroup oioEventLoopGroup=new OioEventLoopGroup();BIO阻塞IO 
    	    *  与 OioServerSocketChannel对应
    	    */
    	  EventLoopGroup group=new NioEventLoopGroup();
    	  
    	  ServerBootstrap bootstrap=new ServerBootstrap();
    	  final EchoServerChannelHandler handler=new EchoServerChannelHandler();
    	  try {
        	  bootstrap.group(group)
        	            .channel(NioServerSocketChannel.class)
        	            .localAddress(new InetSocketAddress(8883))
        	            .childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(handler);	
							};
						});
        	  ChannelFuture channelFuture = bootstrap.bind().sync();
        	  channelFuture.channel().closeFuture().sync();
		}finally {
		    group.shutdownGracefully().sync();	
		}
    	  
       }
       
       public static void main(String[] args) throws InterruptedException {
		new EchoServer(8883).start();
	}
}
