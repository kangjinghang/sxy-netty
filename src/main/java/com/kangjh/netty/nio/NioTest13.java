package com.kangjh.netty.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * unicode(用两个字节表示一个字符)是一种编码方式，而utf是一种存储方式
 * UTF-8,UTF-16,UTF-32是Unicode实现方式之一
 * UTF-8变长字节表示形式,3个字节表示中文,常见英文则采用与ascII码一样的形式采用一个字节
 * BOM(Byte Order Mark)
 * @author kangjinghang
 * @date 2018年12月19日
 */
public class NioTest13 {

	public static void main(String[] args) throws Exception{
		String inputFile = "NioTest13.txt";
		String outputFile = "NioTest13_out.txt";
		
		RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
		RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");
		
		long inputLength = new File(inputFile).length();
		
		FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
		FileChannel outputFileChannel = outputRandomAccessFile.getChannel();
		
		MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);
		
//		Charset charset = Charset.forName("utf-8");
		Charset charset = Charset.forName("iso-8859-1");
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();
		
		CharBuffer charBuffer = decoder.decode(inputData);
		System.out.println(charBuffer.get(14));
		ByteBuffer outputData = encoder.encode(charBuffer);
		
		outputFileChannel.write(outputData);
		
		inputRandomAccessFile.close();
		outputRandomAccessFile.close();
	}
	
}
