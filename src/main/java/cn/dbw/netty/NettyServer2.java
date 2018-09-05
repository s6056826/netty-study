package cn.dbw.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NettyServer2 {
	
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(8883);
			System.out.println("server listening 8883");
			Socket socket = serverSocket.accept();
			BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line=null;
			while((line=reader.readLine())!=null){
				System.out.println("服务端收到："+line);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
