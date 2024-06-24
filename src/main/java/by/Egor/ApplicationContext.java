package by.Egor;

import by.Egor.configuration.Configuration;
import by.Egor.configuration.Instance;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ApplicationContext {

    private final Map<Class<?>, Object> instances = new HashMap<>();

    public ApplicationContext() throws InvocationTargetException, IllegalAccessException {
        Reflections reflections = new Reflections("by.Egor.configuration");
        List<?> configurations = reflections.getTypesAnnotatedWith(Configuration.class)
                .stream()
                .map(type -> {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();// получили список конфигураций (экземпляры),
        // которые лежат в "configuration"
        for (Object configuration : configurations) {
            List<Method> methods = Arrays.stream(configuration.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(Instance.class))
                    .toList();
            List<Method> methodsWithoutParams = methods
                    .stream()
                    .filter(method -> method.getParameters().length == 0)
                    .toList();
            for (Method methodWithoutParams : methodsWithoutParams) {
                instances.put(methodWithoutParams.getReturnType(), methodWithoutParams.invoke(configuration));
            }
            List<Method> methodsWithParams = methods
                    .stream()
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(Instance.class).value()))
                    .filter(method -> method.getParameters().length != 0)
                    .toList();
            for (Method methodWithParams : methodsWithParams) {
                instances.put(
                        methodWithParams.getReturnType(),
                        methodWithParams.invoke(configuration,
                                Arrays.stream(methodWithParams.getParameters())
                                        .map(param -> instances.get(param.getType()))
                                        .toArray())
                );
            }
        }
    }

    public <T> T getInstance(Class<T> type) {
        return (T) Optional.ofNullable(instances.get(type)).orElse(new Object());
    }
    public <T> List<T> getInstances(Class<? extends Annotation> annotation) {
        List<Object> result = new ArrayList<>();
        for (Map.Entry<Class<?>, Object> entry : instances.entrySet()){
            if (entry.getKey().isAnnotationPresent(annotation)) result.add(entry.getValue());
        }
        return (List<T>) result;
    }
}
