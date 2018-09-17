package cn.dbw.netty.ssy10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyServer { 
	
	
	public static void main(String[] args) throws Exception {
	      ServerSocketChannel ssChannel = ServerSocketChannel.open();
	      //设置端口重用
	      ssChannel.socket().setReuseAddress(true);
	      ssChannel.bind(new InetSocketAddress(9999));
	      
	      ByteBuffer buffer = ByteBuffer.allocate(4096);
	      while(true){
	    	  SocketChannel socketChannel = ssChannel.accept();
	    	  long currentTimeMillis = System.currentTimeMillis();
	    	  int readCount=0;
	    	  while(-1!=readCount){
	    		  System.out.println(readCount);
	    		  readCount= socketChannel.read(buffer);
	    		  buffer.clear();
	    	  }
	    	  System.out.println("读取完毕："+(System.currentTimeMillis()-currentTimeMillis));
	      }
	}

}
