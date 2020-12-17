package quiz.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.ir.CatchNode;

public class Converter {

	public static void main(String[] args) throws IOException {
		boolean result;
		File file = new File("mydata.txt");
		try {
			result = file.createNewFile();
			if (result) {
				System.out.println("file created " + file.getCanonicalPath());
			} else {
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace(); // prints exception if any
		}
		readFile();

	}

	private static void readFile() throws IOException {
		// Reads data from questions and answers' file
		File questionFile = new File("Chapter01_Java_Basics.txt");
		FileInputStream ft = new FileInputStream(questionFile);
		DataInputStream in = new DataInputStream(ft);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strline = br.readLine();
		// Reads data from correct answers' file
		File answersFile = new File("Answers_Chapter1.txt");
		FileInputStream ft2 = new FileInputStream(answersFile);
		DataInputStream in2 = new DataInputStream(ft2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		String correctAnswer = br2.readLine();
		int k = 0;
		// writes everything in new file using mark up
		try (Writer writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("MarkedUp.txt")))) {
			while (strline != null) {

				if (Character.isDigit(strline.charAt(0))) {
					if (strline.substring(strline.indexOf(" ") + 1) != null) {
						k++;
						writer.write("~~~" + "\n" + "test" + "\n" + k + "." + strline.substring(strline.indexOf(" "))
								+ "\n");
						strline = br.readLine();
						while (Character.isDigit(strline.charAt(0))) {
							if (strline.substring(strline.indexOf(" ") + 1) != null) {
								writer.write(strline.substring(strline.indexOf(" ") + 1) + "\n");
								strline = br.readLine();
							}
						}
					}
				}
				if (!Character.isDigit(strline.charAt(0)) && strline.charAt(0) != 'I') {
					correctAnswer = br2.readLine();
					while (!Character.isDigit(strline.charAt(0)) && strline.charAt(0) != 'I') {
						if (correctAnswer.substring(correctAnswer.indexOf(".") - 1, correctAnswer.indexOf("."))
								.equals(strline.substring(0, strline.indexOf(" ")))) {
							writer.write(strline + "@1.0" + "\n");

						} else {
							writer.write(strline + "@0.0" + "\n");
						}
						strline = br.readLine();
					}
				} else if (strline.charAt(0) == 'I') {
					writer.write(strline + "\n");
				}
				strline = br.readLine();
			}
			System.out.println("Your file is created and changed");
		} catch (Exception e) {
		}

	}
}