package com.kangjh.netty.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

	public static void main(String[] args) throws Exception{
		InetSocketAddress address = new InetSocketAddress(8899);
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(address);
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
		
		while(true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			serverSocketChannel.configureBlocking(false);
			
			int readCount = 0;
			while(-1 != readCount) {
				try {
					readCount = socketChannel.read(byteBuffer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				byteBuffer.rewind();
			}
			

		}
	}
	
}
