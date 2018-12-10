package com.kangjh.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer，我们随时可以将一个普通buffer调用asReadOnlyBuffer方法返回一个只读buffer，但不能将一个只读buffer转换为一个标准的读写buffer
 * @author kangjinghang
 * @date 2018年12月10日
 */
public class NioTest7 {
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		
		System.out.println(buffer.getClass());
		
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		
		ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
		
		System.out.println(readOnlyBuffer.getClass());
		
		readOnlyBuffer.position(0);
		
		//readOnlyBuffer.put((byte)2);
		
	}

}
