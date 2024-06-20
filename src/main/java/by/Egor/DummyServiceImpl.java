package by.Egor;

import java.util.ArrayList;
import java.util.List;

public class DummyServiceImpl implements DummyService {
    static final List<String> phrases = new ArrayList<>();

    {
        phrases.add("Ты всё сможешь!");
    }

    private static int INDEX = 0;


    @Override
    public List<String> getPhrases() {
        return phrases;
    }

    @Override
    public String getPhrase() {
        return phrases.get(INDEX++ % phrases.size());
    }
}
