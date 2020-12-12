package quiz.core;


import java.util.List;

public interface Questions {
    String question();
    List<String>answers();

    static public Questions of(String question, List<String>answers) {
        return new Questions() {
            @Override
            public String question() {
                return question;
            }

			@Override
			public List<String> answers() {
				return answers;
			}

        };
    }

}