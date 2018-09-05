package cn.dbw.netty.ssy6;

import cn.dbw.netty.ssy6.MultiData.Cat;
import cn.dbw.netty.ssy6.MultiData.MyMessage;
import cn.dbw.netty.ssy6.MultiData.MyMessage.DataType;
import cn.dbw.netty.ssy6.MultiData.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyProtoBufHandler extends SimpleChannelInboundHandler<MultiData.MyMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
		  
		switch (msg.getDataType()) {
		case CatType:
			Cat cat = msg.getCat();
			System.out.println(cat.toString());
			break;
		case StudentType:
			Student student = msg.getStudent();
			System.out.println(student.toString());
			break;
		}
		
	}
   

}