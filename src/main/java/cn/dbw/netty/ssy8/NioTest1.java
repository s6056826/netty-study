package cn.dbw.netty.ssy8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ֱ�ӻ������Ͷѻ�����
 * @author Administrator
 *
 */
public class NioTest1 {
	
	
	
	
	public static void main(String[] args) throws Exception {
		//�ѻ�����
		ByteBuffer bf = ByteBuffer.allocate(1024);
		//ֱ�ӻ�����
		ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);
		
		FileInputStream fileInputStream = new FileInputStream("asad");
		FileChannel fileChannel = fileInputStream.getChannel();
	}

}
