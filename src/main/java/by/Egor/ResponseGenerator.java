package by.Egor;

import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class ResponseGenerator {
    private final Class<? extends Annotation> annotation;
    @Getter
    private final Object controller;
    @Getter
    private final RequestType requestType;

    public ResponseGenerator(Class<? extends Annotation> annotation, Object controller, RequestType requestType) {
        this.annotation = annotation;
        this.controller = controller;
        this.requestType = requestType;
    }

    public Object generateResponse(String URI, Object controller, String parameter) throws InvocationTargetException, IllegalAccessException {
        Method method = findMethodByURI(URI);
        if (method.getParameterCount() > 0) return method.invoke(controller, parameter);
        return method.invoke(controller);
    }

    private Method[] getRequiredMethods() {
        return Arrays
                .stream(controller.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toArray(Method[]::new);
    }

    protected Method findMethodByURI(String URI) {
        Method[] filteredMethods = Arrays
                .stream(getRequiredMethods())
                .filter(method -> {
                    Annotation ann = method.getAnnotation(annotation);
                    if (ann == null) return false;
                    try {
                        Method valueMethod = ann.annotationType().getMethod("value");
                        String value = (String) valueMethod.invoke(ann);
                        return value.equals(URI);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(Method[]::new);

        if (filteredMethods.length > 1) throw new RuntimeException("Multiple methods found");
        if (filteredMethods.length == 0) throw new RuntimeException("No method found");
        return filteredMethods[0];
    }


}
