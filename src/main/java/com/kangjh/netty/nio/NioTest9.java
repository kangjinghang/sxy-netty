package com.kangjh.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NioTest9 {

	public static void main(String[] args) throws Exception {
		RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		
		MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 5);
		
		mappedByteBuffer.put(0,(byte)'a');
		mappedByteBuffer.put(3,(byte)'b');
	}
	
}
