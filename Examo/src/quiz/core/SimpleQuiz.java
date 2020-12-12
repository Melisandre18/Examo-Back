package quiz.core;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleQuiz implements Quiz {
	public Map<String, List<String>> questions = new HashMap<>();

	public SimpleQuiz(List<Questions> words) {
		words.forEach(wp -> {
			putInMap(questions, wp);
		});

	}

	private void putInMap(Map<String, List<String>> map, Questions wp) {
		map.put(wp.question(), wp.answers());
	}

	@Override
	public Map<String, List<String>> getQuestions() {
		return questions;
	}

	public List<String> getAnswers(String question) {
		return questions.get(question);
	}
}