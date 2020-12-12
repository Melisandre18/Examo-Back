package quiz.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ToTxt {
	static Map<String, List<String>> giveninfo = new HashMap<>();
	public static String question;

	public static void main(String[] args) {
		giveninfo = Parser.data();
		for (String key : giveninfo.keySet()) {
			QuizQuestion quest = new QuizQuestion(key, giveninfo.get(key));

			try {
				FileOutputStream f = new FileOutputStream(new File("ToTxt.txt"));
				ObjectOutputStream o = new ObjectOutputStream(f);

				o.writeObject(quest);

				o.close();
				f.close();

				FileInputStream fi = new FileInputStream(new File("ToTxt.txt"));
				ObjectInputStream oi = new ObjectInputStream(fi);
				QuizQuestion ques = (QuizQuestion) oi.readObject();
				System.out.println(ques.toString());
				oi.close();
				fi.close();

			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("Error initializing stream");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
