package by.Egor;

@Controller
public class DummyController {
    private final DummyManager dummyManager;

    public DummyController(DummyManager dummyManager) {
        this.dummyManager = dummyManager;
    }
    @GetMapping("/")
    public String start() {
        return "hello!";
    }
    @GetMapping("/support")
    public String getCheeringPhrase() {
        return dummyManager.getCheeringPhrase();
    }
    @PostMapping("/support")
    public String postCheeringPhrase(String phrase) {
        return dummyManager.addCheeringPhrase(phrase);
    }
}
