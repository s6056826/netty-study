package cn.dbw.netty.chap4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufStudy {
	
	public static void main(String[] args) {
		ByteBuf byteBuf=Unpooled.buffer(1024);
		
		byteBuf.writeByte(1);
		if(byteBuf.hasArray()){
			byte[] array = byteBuf.array();
			
			System.out.println(byteBuf.arrayOffset());
			System.out.println(byteBuf.readerIndex());
			System.out.println(byteBuf.readableBytes());
			System.out.println(byteBuf.readByte());
		}
		ByteBuf directBuf =byteBuf; //get reference form somewhere
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);
            
        }
        //创建一个复合缓冲区
        CompositeByteBuf compositeByteBuf=Unpooled.compositeBuffer();
        
	}

}
