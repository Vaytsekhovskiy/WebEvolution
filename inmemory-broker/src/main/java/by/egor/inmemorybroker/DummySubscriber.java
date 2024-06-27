package by.egor.inmemorybroker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DummySubscriber implements Runnable{
    private final PhraseRepository phraseRepository;
    @Subscriber
    @Override
    public void run() {
        while (true) {
            Phrase phrase = DummyMessageQueue.poll();
            if (phrase != null) {
                phraseRepository.save(phrase);
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
