package servlet;

import Lab1.DBConnector;
import Lab1.Person;
import Lab1.User;
import net.sf.json.JSONObject;
import service.Impl.PersonServiceImpl;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet",urlPatterns = {"/registerServlet"})
public class RegisterServlet extends HttpServlet {
    DBConnector dButil = new DBConnector();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username").trim();
            System.out.println(username);
            String name = request.getParameter("name").trim();
            String password = request.getParameter("password").trim();
            Integer age = Integer.valueOf(request.getParameter("age").trim());
            String telenum = request.getParameter("telenum").trim();

            PersonServiceImpl personService = new PersonServiceImpl();
            UserServiceImpl usersService = new UserServiceImpl();

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            boolean hasUserName = false;
            hasUserName=usersService.isRegistered(username,dButil);
            if (hasUserName) {
                System.out.println("TheUsernameAlreadyExists");
                params.put("Result", "TheUsernameAlreadyExists");
            } else {
                personService.addPerson(new Person(username, name, age, telenum), dButil);
                usersService.changePassword(new User(username, password), dButil);
                params.put("Result", "SignUpSucceed");
            }
            System.out.println(params);
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
        //dButil.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
