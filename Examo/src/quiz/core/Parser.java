package quiz.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Parser {

	private static HashMap<String, List<String>> fromtxt;
	static String answer;
	static double point;

	public static HashMap<String, List<String>> data() {
		
		ReadAndConvert();
		return fromtxt;
	}

	private static void ReadAndConvert() {
		fromtxt = new HashMap<String, List<String>>();
		try {
			File file = new File("txtinfo.txt");
			FileInputStream ft = new FileInputStream(file);
			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline = br.readLine();
			String question = "";

			while (strline != null) {
				List<String> answers = new ArrayList<>();
				List<String> list = new ArrayList<>();
				while (!strline.endsWith("@=")) {
					strline = br.readLine();
					if (strline.contains("@")) {
						list.add(strline);
						answers.add(strline.substring(0, strline.indexOf("@")));
					} else {
						answers.add(strline);
					}
				}
				question = answers.get(0);
				answers.remove(0);
				Collections.shuffle(answers);
				fromtxt.put(question, answers);
				strline = br.readLine();
				strline = br.readLine();
				PointCounter.giveninfo.put(question, list);
				ToTxt.giveninfo.put(question,answers);
			}

			in.close();
		} catch (Exception e) {
		}

	}
}