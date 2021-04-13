package servlet;

import Lab1.DBConnector;
import Lab1.Person;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    DBConnector dButil=new DBConnector();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置相应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            //System.out.println(username);
            UserServiceImpl service = new UserServiceImpl();
            int verigyResult = service.verifyLogin(new User(username,password),dButil);

            Map<String,String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            if(verigyResult == -1){
                params.put("Result","TheUserDoesNotExist");
            }else if(verigyResult == 0){
                params.put("Result","PasswordError");
            }else if(verigyResult == 1){
                params.put("Result","CorrectPassword");
            }

            jsonObject.put("params",params);
            out.write(jsonObject.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
