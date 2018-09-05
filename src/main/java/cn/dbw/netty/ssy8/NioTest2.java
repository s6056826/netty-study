package cn.dbw.netty.ssy8;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * mapbytebuffer的使用（直接缓冲区）
 * 文件内存映射,可以将文件的指定区域内容映射到内存区域中
 * 直接对内存修改可以达到对文件同步修改的目的
 * @author Administrator
 *
 */
public class NioTest2 {

	
	public static void main(String[] args) throws Exception {
		RandomAccessFile randomAccessFile=new RandomAccessFile("D:\\workspace\\netty-study\\src\\main\\resources\\a.txt", "rw");
		FileChannel channel = randomAccessFile.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		
//		while(true) {
//			
//			buffer.clear();
//			
//			int i = channel.read(buffer);
//			if(i==-1) {
//				break;
//			}
//			
//			buffer.flip();
//			
//			while(buffer.hasRemaining()) {
//				System.out.print((char)buffer.get());
//			}
//	
//		}
		//构建文件和内存映射对象
		MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 5);
		//可以直接对内存进行修改，同步到硬盘
		mappedByteBuffer.put(0, (byte)'a');
		randomAccessFile.close();
	}
}
