package cn.dbw.netty.ssy8;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * mapbytebuffer��ʹ�ã�ֱ�ӻ�������
 * �ļ��ڴ�ӳ��,���Խ��ļ���ָ����������ӳ�䵽�ڴ�������
 * ֱ�Ӷ��ڴ��޸Ŀ��Դﵽ���ļ�ͬ���޸ĵ�Ŀ��
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
		//�����ļ����ڴ�ӳ�����
		MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 5);
		//����ֱ�Ӷ��ڴ�����޸ģ�ͬ����Ӳ��
		mappedByteBuffer.put(0, (byte)'a');
		randomAccessFile.close();
	}
}
