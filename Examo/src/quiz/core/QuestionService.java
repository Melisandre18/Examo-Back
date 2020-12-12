package quiz.core;
import java.util.List;


public class QuestionService {

    public interface QuestionBuilder {
        QuestionBuilder setData(List<Questions> words);
        Quiz build();
    }

    static public QuestionBuilder builder() {
        return new Builder();
    }

    static private class Builder implements QuestionBuilder {

        private List<Questions> words;

        @Override
        public QuestionBuilder setData(List<Questions> words) {
            this.words = words;
            return this;
        }

        @Override
        public SimpleQuiz build() {
            return new SimpleQuiz(words);
        }

    }

}
