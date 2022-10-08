package pt.ua.ies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "ServletToValidate", urlPatterns = {"/ServletToValidate"})
public class ServletToValidate extends HttpServlet {
 
  private static final long serialVersionUID = -1915463532411657451L;
 
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    Map<String,String> data = getData();

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
     
    String username = request.getParameter("username");
    String password = request.getParameter("password");
     
    boolean success = validateUser(data, username, password);
     
    try {
      // Write some content
      out.println("<html>");
      out.println("<head>");
      out.println("<title>LoginServlet</title>");
      out.println("</head>");
      out.println("<body>");
   
      if(success) {
        out.println("<h2>Welcome Friend</h2>");
      }else{
        out.println("<h2>Validate your self again.</h2>");
      }
       
      out.println("</body>");
      out.println("</html>");
    } finally {
      out.close();
    }
  }

  private Map<String, String> getData() {
    Map<String, String> data = new HashMap<String, String>();
    data.put("username", "guest");
    return data;
  }

  public boolean validateUser(Map<String, String> data, String username, String password) {
    if (!data.containsKey(username)) return false;
    if (!data.get(username).equals(password)) return false;
    return true;
  }
}