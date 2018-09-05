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
 * 字符类工具使用
 * @author ny
 *
 */
public class CharsetDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//获取当前的类路径
		URL resource = CharsetDemo.class.getResource("");
		String filePath=URLDecoder.decode(resource.getPath(), "utf-8").replaceFirst("/", "");
		RandomAccessFile inAccessFile=new RandomAccessFile(filePath+"in.txt", "r");
		RandomAccessFile outAccessFile=new RandomAccessFile(filePath+"out.txt", "rw");
		
		FileChannel inFileChannel = inAccessFile.getChannel();
		FileChannel outChannel = outAccessFile.getChannel();
		//将文件映射为一个内存中的mapbuffer,底层实现也为bytebuffer
		MappedByteBuffer buffer = inFileChannel.map(MapMode.READ_ONLY, 0, new File(filePath+"in.txt").length());
		//设置字符集编码工具
		Charset charset = Charset.forName("utf-8");
		//分别生成编码器和解码器
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();
		
		CharBuffer charBuffer = decoder.decode(buffer);
		ByteBuffer buffer2 = encoder.encode(charBuffer);
		//将编码后的数据通过通道写入到指定文件中
		outChannel.write(buffer2);
		inAccessFile.close();
		outAccessFile.close();
	}

}
