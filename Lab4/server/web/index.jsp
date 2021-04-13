<%--
  Created by IntelliJ IDEA.
  User: Windows User
  Date: 2020-12-22
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <fieldset id="">
    <legend>注册页面</legend>
    <form action="loginServlet" style="text-align: center">
      <table>
        <tr>
          <td>username：</td>
          <td><input type="text" name="username" /></td>
        </tr>
        <tr>
          <td>
            password：
          </td>
          <td>
            <input type="password" name="password" />
          </td>
        </tr>
      </table>
      <input type="submit" id="btn2" value="提交" />
      <input type="button" id="btn1" value="验证" />
    </form>
  </fieldset>
  </body>
</html>
