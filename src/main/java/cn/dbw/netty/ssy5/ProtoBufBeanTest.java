package cn.dbw.netty.ssy5;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufBeanTest {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		DataInfo.Student student=DataInfo.Student.newBuilder().setName("dbw")
				.setAge(18).setSex("��").build();
		//������ͨ��protobuf��ʽ���л�Ϊ�ֽ����鷽�����־û������紫��
		byte[] byteArray = student.toByteArray();
		//�����л�
		DataInfo.Student student1= student.parseFrom(byteArray);
		System.out.println(student1);
		System.out.println(student1.getName()+"\t"+student1.getAge()+"\t"+student1.getSex());
	}
	
}
