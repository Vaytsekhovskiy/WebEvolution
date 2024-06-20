package by.Egor;

public class DummyManager {
    private final DummyService dummyService;

    public DummyManager(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    public String getCheeringPhrase() {
        return dummyService.getPhrase();
    }

    public String addCheeringPhrase(String phrase) {
        dummyService.getPhrases().add(phrase);
        return "Добавлена фраза: " + phrase;
    }
}