package cn.dbw.netty.ssy9;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
/**
 * �ַ��๤��ʹ��
 * @author ny
 *
 */
public class CharsetDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//��ȡ��ǰ����·��
		URL resource = CharsetDemo.class.getResource("");
		String filePath=URLDecoder.decode(resource.getPath(), "utf-8").replaceFirst("/", "");
		RandomAccessFile inAccessFile=new RandomAccessFile(filePath+"in.txt", "r");
		RandomAccessFile outAccessFile=new RandomAccessFile(filePath+"out.txt", "rw");
		
		FileChannel inFileChannel = inAccessFile.getChannel();
		FileChannel outChannel = outAccessFile.getChannel();
		//���ļ�ӳ��Ϊһ���ڴ��е�mapbuffer,�ײ�ʵ��ҲΪbytebuffer
		MappedByteBuffer buffer = inFileChannel.map(MapMode.READ_ONLY, 0, new File(filePath+"in.txt").length());
		//�����ַ������빤��
		Charset charset = Charset.forName("utf-8");
		//�ֱ����ɱ������ͽ�����
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();
		
		CharBuffer charBuffer = decoder.decode(buffer);
		ByteBuffer buffer2 = encoder.encode(charBuffer);
		//������������ͨ��ͨ��д�뵽ָ���ļ���
		outChannel.write(buffer2);
		inAccessFile.close();
		outAccessFile.close();
	}

}
