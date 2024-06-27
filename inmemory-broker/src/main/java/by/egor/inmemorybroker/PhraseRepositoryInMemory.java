package by.egor.inmemorybroker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PhraseRepositoryInMemory implements PhraseRepository {
    private static final List<Phrase> phrases = new ArrayList<>();
    private static int INDEX;
    static {
        phrases.add(new Phrase("You are nice!"));
    }

    @Override
    public void save(Phrase phrase) {
        phrases.add(phrase);
    }

    @Override
    public void delete(Phrase phrase) {
        phrases.remove(phrase);
    }

    @Override
    public Phrase getPhrase() {
        return phrases.get(INDEX++ % phrases.size());
    }

    @Override
    public List<Phrase> getPhrases() {
        return phrases;
    }
}
