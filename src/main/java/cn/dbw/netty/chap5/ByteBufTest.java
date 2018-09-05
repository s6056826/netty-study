package cn.dbw.netty.chap5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

public class ByteBufTest {
	
	public static void main(String[] args) {
           ByteBuf buf=Unpooled.buffer(1024);
           byte src[]=new byte[12];
           for(int i=0;i<src.length;i++) {
        	   src[i]=(byte)i;
           }
           //将原数字数据写入到缓冲区中
           buf.writeBytes(src);
           buf.writeBytes(Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8));
           while(buf.isReadable()) {
        	   System.out.println("可读字节数："+buf.readableBytes());
        	   System.out.println((char)buf.readByte());
           }
           
           //将ByteBuf切边 派生缓冲区
           ByteBuf slice = buf.slice(0, 12);
           System.out.println(slice.forEachByte(ByteProcessor.FIND_NON_NUL));
           
	}

}
