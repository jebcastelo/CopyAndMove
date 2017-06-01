package com.lmig;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class CopyAndMove {
	public static void write(byte[] bs, String outPath) {
		
		try  {
		RandomAccessFile f = new RandomAccessFile(outPath, "rw");
		f.write(bs);
		f.close();
		} catch (IOException ex){
			System.out.println(ex);
		}
	}

	// public RandomAccessFile(String name, String mode);

	public static void main(String[] args) {
		String filePath = args[0];
		byte[] buf = new byte[100];
		byte[] data = null;
		int dataIdx = 0;
		try {
			RandomAccessFile f = new RandomAccessFile(filePath, "r");
			data = new byte[(int) f.length()];
			while (true) {
				int nBytes = f.read(buf);
				if (nBytes == -1) {
					break;
				}
				for (int i = 0; i < nBytes; i++) {
					data[dataIdx] = buf[i];
					dataIdx++;
				}
			}
			f.close();
		} catch (IOException ex) {
			System.out.println(ex);
			return;
		}
		Charset cs = Charset.forName("UTF-8");
		String s = new String(data, cs);
		System.out.println(s);
		CopyAndMove.write(data, args[1]);
	}
}