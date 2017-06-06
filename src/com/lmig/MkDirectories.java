package com.lmig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MkDirectories {

	public static void write(byte[] bs, String outPath, String operation, String filePath) throws Exception {
		// Files.createDirectories(Paths.get("/path/to/directory"));
		try {
			Path path = Paths.get(outPath);
			Files.createDirectories(path.getParent());
			RandomAccessFile f = new RandomAccessFile(outPath, "rw");
			f.write(bs);
			f.close();
			return;
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	


	public static void main(String[] args) {
		String operation = args[0]; //cp or mv method
		String filePath = args[1];// to read from
		String dest = args[2]; // to move to
		//makeDir(path);
		System.out.println("File Path " + filePath);
		System.out.println(args[0]);
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
		} catch (FileNotFoundException ex) {
			System.out.println("File not found or not valid!");
			return;
		} catch (IOException ex) {
			System.out.println(ex);
			return;
		}
		Charset cs = Charset.forName("UTF-8");
		String s = new String(data, cs);
		System.out.println(s);
		try {
			MkDirectories.write(data, dest, operation, filePath);
			if (operation.equals("mv")){
				System.out.println("In MV");
//				File f = new File (filePath); 
//				System.out.println(filePath);
//				f.delete(); 
				Files.delete(Paths.get(filePath));
			}
		} catch (Exception e) {
			System.out.println("Hey you can't do that!");
			return;
		}
	}
}
