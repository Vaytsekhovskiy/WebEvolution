package by.egor.inmemorybroker;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class DummyMessageQueue {
    @Getter
    private static final BlockingQueue<Phrase> queue = new LinkedBlockingQueue<>();

    public static void publish(Phrase phrase) {
        queue.offer(phrase);
    }
    public static Phrase poll(){
        return queue.poll();
    }
}