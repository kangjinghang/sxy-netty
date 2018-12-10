package com.kangjh.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering和Gathering
 * @author kangjinghang
 * @date 2018年12月10日
 */
public class NioTest11 {

	public static void main(String[] args) throws Exception {
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(8899);
		serverSocketChannel.bind(address);
		
		int messageLength = 1 + 2 + 3;
		
		ByteBuffer[] buffers = new ByteBuffer[3];
		
		buffers[0] = ByteBuffer.allocate(1);
		buffers[1] = ByteBuffer.allocate(2);
		buffers[2] = ByteBuffer.allocate(3);
		
		SocketChannel socketChannel = serverSocketChannel.accept();
		
		while(true) {
			int bytesRead = 0;
			
			while(bytesRead < messageLength) {
				long r = socketChannel.read(buffers);
				bytesRead += r;
				
				System.out.println("bytesRead:" + bytesRead);
				
				Arrays.asList(buffers).stream().map((buffer) -> "position:" + buffer.position() + ",limit:" + buffer.limit()).forEach(System.out::println);
			}
			
			Arrays.asList(buffers).forEach((buffer) -> buffer.flip());
			
			long bytesWritten = 0;
			if(bytesWritten < messageLength) {
				long r = socketChannel.write(buffers);
				bytesWritten += r;
			}
			
			Arrays.asList(buffers).forEach((buffer) -> buffer.clear());
			
			System.out.println("bytesRead:" + bytesRead + ",bytesWritten:" + bytesWritten);
		}
		
	}
	
}
