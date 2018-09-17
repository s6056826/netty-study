package cn.dbw.netty.ssy11.client;

import java.util.concurrent.TimeUnit;

import cn.dbw.netty.ssy11.inter.ChanelHandlerHodler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

/**
 * 链路检测狗，一旦链路状态不稳定，就会尝试重连
 * @author wangxk
 *
 */
@Sharable
public abstract class ConnectWatchingDog extends ChannelInboundHandlerAdapter implements TimerTask,ChanelHandlerHodler {
    
	private Bootstrap bootstrap;
	private Timer timer;
	private String host;
	private int port;
	private boolean isRetry;
	private int attempts=0;
	
	public ConnectWatchingDog(Bootstrap bootstrap, Timer timer, String host,
			int port, boolean isRetry, int attempts) {
		this.bootstrap = bootstrap;
		this.timer = timer;
		this.host = host;
		this.port = port;
		this.isRetry = isRetry;
		this.attempts = attempts;
	}
	/**
	 * 当连接激活时，使重试次数归0
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("当前链路已经激活了，重连尝试次数重新置为0");
		attempts=0;
		ctx.fireChannelActive();
	}
	
	
	/**
	 * 当连接不稳定，断开的时候，触发重连机制
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("链接关闭");
        if(isRetry){
            System.out.println("链接关闭，将进行重连");
        	if(attempts<12){
        		attempts++;
        		//重连次数越多，延期执行时间越长
        		int delay=2<<attempts;
        		timer.newTimeout(this, delay, TimeUnit.SECONDS);
        		 System.out.println("重连次数："+attempts);
        	}
        }
		super.channelInactive(ctx);
	}

	public void run(Timeout timeout) throws Exception {
		ChannelFuture future;
		synchronized (bootstrap) {
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(handlers());
				}
			});
			future=bootstrap.connect(host, port);
		}
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				boolean success = future.isSuccess();
				//若重连没有成功，则继续尝试重连
				if(!success){
					System.out.println("重连失败");
					future.channel().pipeline().fireChannelInactive();
				}else{
					System.out.println("重连成功");
				}
			}
		});
		
	}


}
