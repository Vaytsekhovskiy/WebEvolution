package by.Egor;

import by.Egor.ApplicationContext;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextTest {
    @Test
    public void application_context_should_return_instance_by_class() throws InvocationTargetException, IllegalAccessException {
        final ApplicationContext applicationContext = new ApplicationContext();
        assertEquals(DummyManager.class, applicationContext.getInstance(DummyManager.class).getClass());
    }
}
