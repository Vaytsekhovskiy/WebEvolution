package by.Egor.generators;

import by.Egor.GetMapping;
import by.Egor.RequestType;
import by.Egor.ResponseGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Generator
public class GetResponseGenerator extends ResponseGenerator {
    public GetResponseGenerator(Object controller) {
        super(GetMapping.class, controller, RequestType.GET);
    }

}
