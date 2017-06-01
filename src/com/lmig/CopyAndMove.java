package com.lmig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class CopyAndMove {
	public static void write(byte[] bs, String outPath) throws Exception {
		
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
		} 
		catch (FileNotFoundException ex) {
			System.out.println("File not found or not valid!");
			return;
		}
		catch (IOException ex) {
			System.out.println(ex);
			return;
		}
		Charset cs = Charset.forName("UTF-8");
		String s = new String(data, cs);
		System.out.println(s);
		try {
			CopyAndMove.write(data, args[1]);
		} catch (Exception e) {
			System.out.println("Hey you can't do that!");
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}