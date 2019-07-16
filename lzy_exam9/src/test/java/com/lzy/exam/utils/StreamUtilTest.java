package com.lzy.exam.utils;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.junit.Test;

public class StreamUtilTest {

	@Test
	public void testCloseAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() throws IOException {
		InputStream is = new FileInputStream(new File("D:/data.txt"));
//		OutputStream ios = new FileOutputStream(new File("D:/wxc.txt"));
		String copy = StreamUtil.copy(is,null,true, false);
		System.out.println(copy);
	}

	@Test
	public void testReadTextFileInputStream() throws IOException {
		InputStream is = new FileInputStream(new File("D:/data.txt"));
		String readTextFile = StreamUtil.readTextFile(is);
		System.out.println(readTextFile);
	}

	@Test
	public void testReadTextFileFile() throws FileNotFoundException, IOException {
		File file = new File("D:/data.txt");
		String readTextFile = StreamUtil.readTextFile(file);
		System.out.println(readTextFile);
	}

	@Test
	public void testReadStringFromSystemIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你的姓名");
		String stringFromSystemIn = StreamUtil.readStringFromSystemIn(sc.next());
		System.out.println(stringFromSystemIn);
	}

	@Test
	public void testReadIntFromSystemIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你的年龄");
		int stringFromSystemIn = StreamUtil.readIntFromSystemIn(sc.nextLine());
		System.out.println(stringFromSystemIn);
		
	}

}
