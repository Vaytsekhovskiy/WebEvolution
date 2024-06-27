package by.egor.inmemorybroker;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository {
    void save(Phrase phrase);
    void delete(Phrase phrase);
    Phrase getPhrase();
    List<Phrase> getPhrases();
}
