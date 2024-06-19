import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DummyHttpServlet extends HttpServlet {
    private DummyHttpManager manager;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.manager = new DummyHttpManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().append(manager.getCheeringPhrase());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().append(manager.addCheeringPhrase(req.getParameter("phrase")));
    }
}