package by.Egor;

import by.egor.inmemorybroker.Phrase;

import java.util.List;

public interface DummyService {
    List<Phrase> getPhrases();

    Phrase getPhrase();

    Phrase addPhrase(Phrase phrase);
}
