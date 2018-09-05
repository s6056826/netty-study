package cn.dbw.netty.ssy8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区和堆缓冲区
 * @author Administrator
 *
 */
public class NioTest1 {
	
	
	
	
	public static void main(String[] args) throws Exception {
		//堆缓冲区
		ByteBuffer bf = ByteBuffer.allocate(1024);
		//直接缓冲区
		ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);
		
		FileInputStream fileInputStream = new FileInputStream("asad");
		FileChannel fileChannel = fileInputStream.getChannel();
	}

}
