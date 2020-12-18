package quiz.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Parser {

	public static void main(String[] args) throws IOException {
		boolean result;
		boolean result1;
		File file = new File("ParsedQuestions.txt");
		File file2 = new File("ParsedAnswers.txt");

		try {
			result = file.createNewFile();
			result1 = file2.createNewFile();
			if (result && result1) {
				System.out.println("files created " + "\n" + file.getCanonicalPath() + "\n" + file2.getCanonicalPath());
			} else {
				System.out.println("Files already exist at location: " + "\n" + file.getCanonicalPath() + "\n"
						+ file2.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace(); // prints exception if any
		}
		ReadAndParse();
	}

	private static void ReadAndParse() {
		try {
			File file = new File("MarkedUp.txt");
			FileInputStream ft = new FileInputStream(file);
			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline = br.readLine();
			String question = "";
			int counter = 0;
			strline = br.readLine();
			try (Writer writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ParsedQuestions.txt")))) {
				try (Writer correctwriter = new PrintWriter(
						new OutputStreamWriter(new FileOutputStream("ParsedAnswers.txt")))) {
					
					while (strline != null) {
						List<String> answers = new ArrayList<>();
						List<String> ranswers = new ArrayList<>();
						while (!strline.contains("???")) {
							strline = br.readLine();
							if (strline.contains("@")) {
								answers.add(strline.substring(0, strline.indexOf("@")));

							} else {
								answers.add(strline);
							}
							if (strline.contains("1.0")) {
								counter++;
								ranswers.add(counter + strline.substring(0, 1));

							}

						}
						
						strline = br.readLine();
						ranswers.set(0, ranswers.get(0)+"."+" "+strline);
						strline = br.readLine();
						strline = br.readLine();
						question = answers.get(0);
						answers.remove(0);
						answers.remove(answers.size() - 1);
						writer.write(question + "\n");
						for (int i = 0; i < answers.size(); i++) {
							writer.write(answers.get(i) + "\n");
						}
						correctwriter.write(ranswers.get(0)+"\n");
						
						
					}
				}
			}

			in.close();
		} catch (Exception e) {
		}

	}
}