package by.Egor.generators;

import by.Egor.PostMapping;
import by.Egor.RequestType;
import by.Egor.ResponseGenerator;
import by.Egor.configuration.Configuration;

@Generator
public class PostResponseGenerator extends ResponseGenerator {
    public PostResponseGenerator(Object controller) {
        super(PostMapping.class, controller, RequestType.POST);
    }
}
