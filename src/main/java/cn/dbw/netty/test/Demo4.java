package cn.dbw.netty.test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo4 {
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入一个数字:");
		int nextInt=0;
		int totolNum=1;
		try {
			nextInt= scanner.nextInt();
			for(int i=nextInt;i>=1;i--){
				totolNum=i*totolNum;
			}
			System.out.println("阶乘为："+totolNum);
		} catch (Exception e) {
			if(e instanceof InputMismatchException){
				System.err.println("请输入合法的数字！");
			}
		}
		
	}

}
