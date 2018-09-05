package cn.dbw.netty.test;

import java.util.Scanner;

public class Demo3 {
	
	/**
	 * 输入一行字符，分别统计出其英文字母、空格、数字和其它字符的个数；
	 */
	public static void main(String[] args) {
		int num=0,letter=0,space=0,other=0;
		Scanner scanner=new Scanner(System.in);
		System.out.print("请输入字符：");
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
		System.out.println("数字:"+num+"个，   字符:"+letter+"个，    空格:"+space+"个，    其它:"+other+"个");
	}

}
