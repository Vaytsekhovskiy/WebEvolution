package by.Egor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DummyServletTest {

    @InjectMocks
    private DummyServlet servlet;

    @Mock
    private DummyManager manager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private StringWriter responseWriter;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGet() throws Exception {
        String testPhrase = "Тестовая фраза";
        doReturn(testPhrase).when(manager).getCheeringPhrase();

        servlet.doGet(request, response);

        verify(manager, times(1)).getCheeringPhrase();
        assertEquals(testPhrase, responseWriter.toString());
    }
    @Test
    public void testDoPost() throws Exception {
        String testPhrase = "Тестовая фраза";
        String testResponse = "Добавлена фраза: Тестовая фраза";

        doReturn(testResponse).when(manager).addCheeringPhrase(testPhrase);
        doReturn(testPhrase).when(request).getParameter("phrase");
        servlet.doPost(request, response);

        assertEquals(testResponse, responseWriter.toString());
    }
}