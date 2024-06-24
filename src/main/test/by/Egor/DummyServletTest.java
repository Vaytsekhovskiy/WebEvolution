package by.Egor;

import by.Egor.generators.Generator;
import by.Egor.generators.GetResponseGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DummyServletTest {

    @InjectMocks
    private DummyServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private DummyController controller; // mockito сопоставляет с DummyServlet.controller

    private StringWriter responseWriter;
    @Mock
    private DummyController manager;

    @Before
    public void setUp() throws IOException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
        ApplicationContext applicationContext = new ApplicationContext();
        List<ResponseGenerator> responseGeneratorList = applicationContext.getInstances(Generator.class);
        responseGeneratorList.forEach(responseGenerator
                -> servlet.getType2generator().put(responseGenerator.getRequestType(), responseGenerator));
    }

    @Test
    public void servlet_should_return_phrase() throws IOException {
        String testPhrase = "Тестовая фраза";
        String getMethod = "GET";
        String URI = "/support";
        doReturn(testPhrase).when(controller).getCheeringPhrase();
        doReturn(getMethod).when(request).getMethod();
        doReturn(URI).when(request).getRequestURI();

        servlet.service(request, response);

        //verify(manager, times(1)).getCheeringPhrase();
        assertEquals(testPhrase, responseWriter.toString());
    }
}