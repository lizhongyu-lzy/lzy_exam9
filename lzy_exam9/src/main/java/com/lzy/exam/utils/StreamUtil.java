package com.lzy.exam.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class StreamUtil {

	/**
	 * 方法1：批量关闭流，参数能传无限个,例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	 * 
	 * @param closeables
	 */
	public static void closeAll(Closeable... closeables) {
		for (Closeable closeable : closeables) {
			if (closeable == null) {
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 
	 * 方法2：拷贝流，将InputStream流拷到OutputStream，可以用于快速读与文件、上传下载，为了提高性能， 需要使用缓冲。 参数1：输入流
	 * 参数2：输出流 参数3：处理完成后是否关闭输入流 参数4：处理完成后是否关闭输出流 //示例:
	 * 
	 * @return
	 */
	public static String copy(InputStream is, OutputStream out, boolean isCloseInputStream, boolean isCloseOutputStream)

			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
		StringBuffer sb = new StringBuffer();
		String str = "";
		BufferedWriter bw = null ;

		if (out != null) {
			bw = new BufferedWriter(new OutputStreamWriter(out));
			while ((str = br.readLine()) != null) {
				bw.write(str);
			}
		}else {
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			
		}


		if (isCloseInputStream)

		{
			closeAll(br);
		}
		if (isCloseOutputStream) {
			bw.flush();
			closeAll(bw);
		}
		return sb.toString();

	}

	/**
	 * 方法3：传入一个文本文件对象， 默认为UTF-8编码，返回该文件内容， 要求方法内部调用上面第2个方法拷贝流， 结束后第1个方法关闭流
	 * 
	 * @param src
	 * @return
	 * @throws IOException
	 */
	// 示例:
	public static String readTextFile(InputStream src) throws IOException {
		return copy(src,null,true,false);
	}

	/**
	 * 方法4：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第3个方法
	 * 
	 * @param txtFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	// 示例:
	public static String readTextFile(File txtFile) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(txtFile);
		return copy(is,null,true,false);
	}

	/**
	 * 方法5：从控制台读取用户输入的字符串。 参数message：
	 * 用于控制台提示。例如“请输出您的姓名：”，
	 * 然后用户输入姓名后回车，* 方法开始执行并读取姓名。
	 */
	public static String readStringFromSystemIn(String message) {
		
		return message;
	}

	/**
	 * 方法6：从控制台读取用户输入的数字。 参数message：用于控制台提示。例如“请输出您的年龄：”，
	 * 然后用户输入年龄后回车，*
	 * 方法开始执行并读取年龄，如何用户输出的不是数字，或是空值（直接回车），则继续提示输入。
	 */
	public static int readIntFromSystemIn(String message) {
		boolean falg = true;
			
		String str = "";
		try {
			int parseInt = Integer.parseInt(message);
			System.out.println(parseInt);
			str += parseInt;
			falg = true;
		} catch (Exception e) {
			falg = false;
		}
		System.out.println(falg);

		if(!falg) {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入你的年龄");
			readIntFromSystemIn(sc.next());
		}
		int parseInt = Integer.parseInt(str);
		System.out.println(parseInt);
		return parseInt;
		
	}

}
