package quiz.core;

import java.io.Serializable;
import java.util.List;

public class QuizQuestion implements Serializable {
	private String question;
	private List<String> answers;

	QuizQuestion(String question, List<String> answers) {
		this.question = question;
		this.answers = answers;
	};

	public String toString() {
		return question + "\n" + answers;
	}
}
