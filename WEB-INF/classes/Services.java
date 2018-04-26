import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Services
 */
public class Services extends HttpServlet {
    ProjectScreening services = new ProjectScreening();

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	//Obtener acci�n a realizar
    	String action = request.getParameter("bEnviar");
    	
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("action");
        String ans    = "";

        switch (method) {
            case "npv":
                ans = services.calculateNPV(request.getParameter("data"));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(ans);
                break;
            
            case "pbp":
                ans = services.calculatePaybackPeriod(request.getParameter("data"));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(ans);
                break;
            
            case "dep":
                ans = services._getDepreciation(request.getParameter("data"));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(ans);
                break;

            default:
                break;
        }
    }
}