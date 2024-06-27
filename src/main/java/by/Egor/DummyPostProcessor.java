package by.Egor;

import by.egor.inmemorybroker.Subscriber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DummyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Arrays
                .stream(bean.getClass().getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Subscriber.class))) {
            Thread thread = new Thread((Runnable) bean);
            thread.start();
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
