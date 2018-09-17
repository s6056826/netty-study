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
 * ��·��⹷��һ����·״̬���ȶ����ͻ᳢������
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
	 * �����Ӽ���ʱ��ʹ���Դ�����0
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("��ǰ��·�Ѿ������ˣ��������Դ���������Ϊ0");
		attempts=0;
		ctx.fireChannelActive();
	}
	
	
	/**
	 * �����Ӳ��ȶ����Ͽ���ʱ�򣬴�����������
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("���ӹر�");
        if(isRetry){
            System.out.println("���ӹرգ�����������");
        	if(attempts<12){
        		attempts++;
        		//��������Խ�࣬����ִ��ʱ��Խ��
        		int delay=2<<attempts;
        		timer.newTimeout(this, delay, TimeUnit.SECONDS);
        		 System.out.println("����������"+attempts);
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
				//������û�гɹ����������������
				if(!success){
					System.out.println("����ʧ��");
					future.channel().pipeline().fireChannelInactive();
				}else{
					System.out.println("�����ɹ�");
				}
			}
		});
		
	}


}
