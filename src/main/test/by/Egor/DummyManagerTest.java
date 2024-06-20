package by.Egor;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;

public class DummyManagerTest {
    @Test
    public void dummy_manager_should_return_phrase() throws InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = new ApplicationContext();
        DummyManager dummyManager = applicationContext.getInstance(DummyManager.class);
        Assertions.assertEquals("Ты всё сможешь!", dummyManager.getCheeringPhrase());
    }
}
