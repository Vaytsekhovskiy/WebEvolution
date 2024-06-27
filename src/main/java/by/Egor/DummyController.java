package by.Egor;

import by.egor.inmemorybroker.Phrase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyController {
    private final DummyService dummyService;

    @GetMapping("/")
    public String start() {
        return "hello!";
    }

    @GetMapping("/support")
    public Phrase getCheeringPhrase() {
        return dummyService.getPhrase();
    }

    @PostMapping("/support")
    public Phrase addCheeringPhrase(@RequestBody Phrase phrase) {
        return dummyService.addPhrase(phrase);
    }
}
