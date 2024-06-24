package by.Egor;

import by.Egor.generators.Generator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyServlet extends HttpServlet {
    private Object controller;
    @Getter
    private Map<RequestType, ResponseGenerator> type2generator = new HashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext applicationContext;
        try {
            applicationContext = new ApplicationContext();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        this.controller = applicationContext.getInstance(DummyController.class);
        List<ResponseGenerator> responseGeneratorList = applicationContext.getInstances(Generator.class);
        responseGeneratorList.forEach(responseGenerator
                -> type2generator.put(responseGenerator.getRequestType(), responseGenerator));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Object generatedResponse;
        String parameter = req.getParameter("phrase");
        try {
            generatedResponse = type2generator
                    .get(RequestType.valueOf(req.getMethod())).generateResponse(req.getRequestURI().replace("/WebEvol_war", ""), controller, parameter);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().append((String) generatedResponse);
    }
}