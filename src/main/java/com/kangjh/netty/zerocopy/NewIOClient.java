package com.kangjh.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

	public static void main(String[] args) throws Exception{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost",8899));
		socketChannel.configureBlocking(true);
		
		String fileName = "F:/abc.pdf";
		
		FileChannel fileChannel = new FileInputStream(fileName).getChannel();
		
		long startTime = System.currentTimeMillis();
		
		long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
		
		//126
		System.out.println("发送总字节数：" + transferCount + ",耗时：" + (System.currentTimeMillis() - startTime));
		
	}
	
}
