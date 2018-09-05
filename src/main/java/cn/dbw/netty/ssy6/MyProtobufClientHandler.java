package cn.dbw.netty.ssy6;

import java.util.Random;

import cn.dbw.netty.ssy6.MultiData.MyMessage;
import cn.dbw.netty.ssy6.MultiData.MyMessage.DataType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyProtobufClientHandler extends SimpleChannelInboundHandler<MultiData.MyMessage> {
   
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Random random=new Random();
		int i = random.nextInt(2);
		switch (i) {
		case 0:
			MultiData.MyMessage student=MultiData.MyMessage.newBuilder().setDataType(DataType.StudentType)
		       .setStudent(MultiData.Student.newBuilder()
		         .setName("dbw")
		         .setAge(18)
		         .setSex("nan").build())
		      .build();
			ctx.writeAndFlush(student);
			break;

		default:
			MultiData.MyMessage cat=MultiData.MyMessage.newBuilder().setDataType(DataType.CatType)
            .setCat(MultiData.Cat.newBuilder()
          		  .setName("cat")
          		  .setColor("»ÆÉ«").build())
            .build();
			ctx.writeAndFlush(cat);
			break;
		}		
	
	}
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
