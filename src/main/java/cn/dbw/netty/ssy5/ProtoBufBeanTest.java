package cn.dbw.netty.ssy5;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufBeanTest {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		DataInfo.Student student=DataInfo.Student.newBuilder().setName("dbw")
				.setAge(18).setSex("男").build();
		//将对象通过protobuf方式序列化为字节数组方便对象持久化或网络传输
		byte[] byteArray = student.toByteArray();
		//反序列化
		DataInfo.Student student1= student.parseFrom(byteArray);
		System.out.println(student1);
		System.out.println(student1.getName()+"\t"+student1.getAge()+"\t"+student1.getSex());
	}
	
}
