import java.util.ArrayList;
import java.util.List;

public class DummyHttpManager {
    static final List<String> phrases = new ArrayList<>();
    private static int INDEX = 0;

    static {
        phrases.add("У тебя всё получится!");
    }

    public String getCheeringPhrase() {
        return phrases.get(INDEX++ % phrases.size());
    }

    public String addCheeringPhrase(String phrase) {
        phrases.add(phrase);
        return "Добавлена фраза: " + phrase;
    }
}
