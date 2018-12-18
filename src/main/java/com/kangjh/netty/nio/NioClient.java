package com.kangjh.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
	
	public static void main(String[] args) throws Exception{
		try {
			
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			Socket socket = socketChannel.socket();
			
			Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));
			
			while(true) {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				
				for (SelectionKey selectionKey : selectedKeys) {
					if(selectionKey.isConnectable()) {
						SocketChannel client = (SocketChannel)selectionKey.channel();
						if(client.isConnectionPending()) {
							client.finishConnect();
							
							ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
							
							writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
							writeBuffer.flip();
							
							socketChannel.write(writeBuffer);
							
							ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
							executorService.submit(() -> {
								try {
									writeBuffer.clear();
									InputStreamReader reader = new InputStreamReader(System.in);
									BufferedReader br = new BufferedReader(reader);
									
									String sendMessage = br.readLine();
									
									writeBuffer.put(sendMessage.getBytes());
									writeBuffer.flip();
									client.write(writeBuffer);
								} catch (Exception e) {
									e.printStackTrace();
								}
							});
							
						}
						client.register(selector, SelectionKey.OP_READ);
					}else if (selectionKey.isReadable()) {
						SocketChannel client = (SocketChannel)selectionKey.channel();
						
						ByteBuffer readBuffer = ByteBuffer.allocate(1024);
						
						int count = client.read(readBuffer);
						
						if(count > 0) {
							String receviedMessage = new String(readBuffer.array(), 0 ,count);
							System.out.println(receviedMessage);
						}
					}
				}
				selectedKeys.clear();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
