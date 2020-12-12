package quiz.cli;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import quiz.core.PointCounter;

import quiz.core.QuestionService;
import quiz.core.Questions;
import quiz.core.Quiz;
import quiz.core.Parser;
import quiz.core.ToTxt;

public class QuiCL {
	public static void main(String[] args) {
		HashMap<String, List<String>> givendata = Parser.data();
		List<Questions> data = new ArrayList<>();
		for (String key : givendata.keySet()) {
			data.add(Questions.of(key, givendata.get(key)));
		}
		System.out.println("You should see you questions below !");
		System.out.println();

		Quiz q = QuestionService.builder().setData(data).build();
		Scanner scanner = new Scanner(System.in);
		q.getQuestions().keySet().forEach(que -> {
			System.out.println(que);
			if (q.getQuestions().get(que).size() != 1)
				System.out.println(q.getQuestions().get(que));
			
			System.out.println("Your answer: ");
			String answer = scanner.nextLine();
			PointCounter.answer=answer;
			PointCounter.question=que;
			ToTxt.question=que;
			System.out.println(PointCounter.point());
			
		});
		System.out.println("You finished your quiz!");
		System.out.println("Your final point: "+PointCounter.pointer);
		scanner.close();

	}
}