package cn.dbw.netty.ssy8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 关于buffer的Scattering(分散)和Gathering(聚合)
 * @param args
 */
public class NioTest3 {

	
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssocketChannel = ServerSocketChannel.open();
		ssocketChannel.socket().bind(new InetSocketAddress(8809));
		
		ByteBuffer[] buffers=new ByteBuffer[3];
		buffers[0]=ByteBuffer.allocate(2);
		buffers[1]=ByteBuffer.allocate(3);
		buffers[2]=ByteBuffer.allocate(4);
		
		long msgLength=2+3+4;
		
		SocketChannel socketChannel = ssocketChannel.accept();
		System.out.println("客户端接入成功。。。");
		
		while(true) {
			
			long readLength=0;
			
			while(readLength<msgLength) {
				long read = socketChannel.read(buffers);
				readLength+=read;
				for(int i=0;i<3;i++) {
					System.out.println("position:"+buffers[i].position()+"\tlimit"+buffers[i].limit());
				}
				System.out.println("bufferReadLength:"+readLength);
			}
			for(int i=0;i<3;i++) {
				buffers[i].flip();
			}
			
			
			long writeLength=0;
			if(writeLength<msgLength) {
				long write = socketChannel.write(buffers);
				writeLength+=write;
				System.out.println("writeLength:"+writeLength);
			}
			
			for(int i=0;i<3;i++) {
				buffers[i].clear();
			}
            System.out.println("byteRead ："+readLength+ ",bytesWritee:"+writeLength+",messageLenth:"+msgLength);
		}
		
	}
}
