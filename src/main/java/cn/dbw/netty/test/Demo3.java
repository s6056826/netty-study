package cn.dbw.netty.test;

import java.util.Scanner;

public class Demo3 {
	
	/**
	 * ����һ���ַ����ֱ�ͳ�Ƴ���Ӣ����ĸ���ո����ֺ������ַ��ĸ�����
	 */
	public static void main(String[] args) {
		int num=0,letter=0,space=0,other=0;
		Scanner scanner=new Scanner(System.in);
		System.out.print("�������ַ���");
		String str = scanner.nextLine();
		for(int i=0;i<str.length();i++){
			char oneChar = str.charAt(i);
			if(Character.isDigit(oneChar)){
				num++;
			}else if(Character.isLetter(oneChar)){
				letter++;
			}else if(Character.isSpace(oneChar)){
				space++;
			}else{
				other++;
			}
		}
		System.out.println("����:"+num+"����   �ַ�:"+letter+"����    �ո�:"+space+"����    ����:"+other+"��");
	}

}
