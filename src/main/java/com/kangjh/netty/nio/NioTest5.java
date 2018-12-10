package com.kangjh.netty.nio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer类型化的put和get方法
 * @author kangjinghang
 * @date 2018年12月10日
 */
public class NioTest5 {
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(64);
		
		buffer.putInt(5);
		buffer.putLong(5000000000L);
		buffer.putDouble(14.123456);
		buffer.putChar('你');
		buffer.putShort((short)2);
		buffer.putChar('我');
		
		buffer.flip();
		
		System.out.println(buffer.getInt());
		System.out.println(buffer.getLong());
		System.out.println(buffer.getDouble());
		System.out.println(buffer.getChar());
		System.out.println(buffer.getShort());
		System.out.println(buffer.getChar());
		
	}

}
