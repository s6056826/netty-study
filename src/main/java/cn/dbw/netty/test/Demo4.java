package cn.dbw.netty.test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo4 {
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("������һ������:");
		int nextInt=0;
		int totolNum=1;
		try {
			nextInt= scanner.nextInt();
			for(int i=nextInt;i>=1;i--){
				totolNum=i*totolNum;
			}
			System.out.println("�׳�Ϊ��"+totolNum);
		} catch (Exception e) {
			if(e instanceof InputMismatchException){
				System.err.println("������Ϸ������֣�");
			}
		}
		
	}

}
