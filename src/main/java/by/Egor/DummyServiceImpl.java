package by.Egor;

import by.egor.inmemorybroker.DummyMessageQueue;
import by.egor.inmemorybroker.Phrase;
import by.egor.inmemorybroker.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyServiceImpl implements DummyService {

    private final PhraseRepository phraseRepository;


    @Override
    public List<Phrase> getPhrases() {
        return phraseRepository.getPhrases();
    }

    @Override
    public Phrase getPhrase() {
        return phraseRepository.getPhrase();
    }

    @Override
    public Phrase addPhrase(Phrase phrase) {
        DummyMessageQueue.publish(phrase);
        return phrase;
    }
}
