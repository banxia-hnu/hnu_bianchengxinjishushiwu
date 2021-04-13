package servlet;

import Lab1.DBConnector;
import Lab1.User;
import net.sf.json.JSONObject;
import service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ChangePasswordServlet",urlPatterns = {"/changePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {
    DBConnector dButil = new DBConnector();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            UserServiceImpl service = new UserServiceImpl();

            boolean change = service.changePassword(new User(username,password),dButil);
            Map<String,String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            if(change){
                params.put("Result","ChangeSucceed");
            }else{
                params.put("Result","ChangeFail");
            }
            jsonObject.put("params",params);
            out.write(jsonObject.toString());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
