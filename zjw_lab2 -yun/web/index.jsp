<%@ page import="com.zjw.DButil" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: zjw
  Date: 2019/11/1
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<html>
  <head>
      <title>操作首页</title>
      <!-- 指定字符集 -->
      <meta charset="utf-8">
      <!-- 使用Edge最新的浏览器的渲染方式 -->
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
      width: 默认宽度与设备的宽度相同
      initial-scale: 初始的缩放比，为1:1 -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
      <title>添加用户</title>

      <!-- 1. 导入CSS的全局样式 -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
      <script src="js/jquery-2.1.0.min.js"></script>
      <!-- 3. 导入bootstrap的js文件 -->
      <script src="js/bootstrap.min.js"></script>
      <script type="text/javascript">
          function checkAge() {
              var flag = true;
              var ele = document.getElementById("age");
              var regNumber = /^[0-9]+$/ ;
              if(ele.value==null||ele.value=="") return flag;
              if(!regNumber.test(ele.value)) {
                  alert("Age's type should be integer!");
                  flag = false;
              }
              if(flag) document.getElementById("agespan").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              else document.getElementById("agespan").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"
              return flag;
          }
          function checkName() {
              var flag = true;
              var ele = document.getElementById("name");
              var regNumber = /^.{0,20}$/ ;
              if(ele.value==""||ele.value==null){
                  return false;
              }
              if(!regNumber.test(ele.value)) {
                  alert("Name's length should be no more than 20!");
                  flag = false;
              }
              if(flag) document.getElementById("namespan").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              else document.getElementById("namespan").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"
              return flag;
          }
          function checkTelenum() {
              var flag = true;
              var ele = document.getElementById("teleno");
              var regNumber = /^1[3456789]\d{9}$/ ;
              if(ele.value==""||ele.value==null) return flag;
              if(!regNumber.test(ele.value)) {
                  alert("Telenum's format is wrong!");
                  flag = false;
              }
              if(flag) document.getElementById("telenumspan").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              else document.getElementById("telenumspan").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"
              return flag;
          }
          function checkUsername1() {
              var flag = true;
              var ele = document.getElementById("username1");
              var regNumber = /^.{0,10}$/ ;
              if(ele.value==""||ele.value==null) {
                  return false;
              }
              else if(!regNumber.test(ele.value)) {
                  alert("Username's length should be no more than 10!");
                  flag = false;
              }
              if(flag) document.getElementById("username1span").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              else document.getElementById("username1span").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"
              return flag;
          }
          function checkUsername2() {
              var flag = true;
              var ele = document.getElementById("username2");
              var regNumber = /^.{0,10}$/ ;
              if(ele.value==""||ele.value==null) {
                  alert("Please input user's username !");
                  document.getElementById("username2span").innerHTML=
                      "<font style='color:red;font-size:15px' <b>username不能为空</b> </font>"
                  return false;
              }
              else if(!regNumber.test(ele.value)) {
                  alert("Username's length should be no more than 10!");
                  flag = false;
              }
              if(flag) {

                  document.getElementById("username2span").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              }
              else document.getElementById("username2span").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"
              return flag;
          }
          function checkSubmit1() {
              if(document.getElementById("username1").value==null||document.getElementById("username1").value==""){
                  alert("Please input person'username !");
                  document.getElementById("username1span").innerHTML=
                      "<font style='color:red;font-size:15px' <b>username不能为空</b> </font>"
                  return false;
              }
              if (document.getElementById("name").value==null||document.getElementById("name").value==""){
                  alert("Please input person'name !");
                  document.getElementById("namespan").innerHTML=
                      "<font style='color:red;font-size:15px' <b>name不能为空</b> </font>"
                  return false;
              }
              var flag = checkTelenum()&&checkAge()&&checkUsername1()&&checkName();
              if(!flag){
                  alert("Submit Error ! ")
              }
              return flag;
          }
          function checkSubmit2() {
              var flag = true;
              var ele = document.getElementById("username2");
              var regNumber = /^.{0,10}$/ ;
              if(ele.value==""||ele.value==null) {
                  alert("Please input user's username !");
                  document.getElementById("username2span").innerHTML=
                      "<font style='color:red;font-size:15px' <b>username不能为空</b> </font>"
                  return false;
              }
              else if(!regNumber.test(ele.value)) {
                  alert("Username's length should be no more than 10!");
                  flag = false;
              }
              if(flag) document.getElementById("username2span").innerHTML=
                  "<font style='color:green;font-size:15px' <b>输入格式正确</b> </font>"
              else document.getElementById("username2span").innerHTML=
                  "<font style='color:red;font-size:15px' <b>输入格式错误</b> </font>"

              return flag;
          }
      </script>
  </head>
  <body background="/img/4.jpg">
    <%
        DButil dButil = new DButil();
        application.setAttribute("DB",dButil);
        System.out.println(new Date());
    %>

    <div class="container">

      <center> <h2
      >数据库表person插入信息</h2> </center>
      <form action="${pageContext.request.contextPath}/addPersonServlet" onsubmit="return checkSubmit1()" method="post">
<%--          <input type="hidden" name="type" value="person">--%>
          <div class="form-group">
              <%--style="width: 200px; height: 50px"--%>
          <center><label for="username1">username：</label><span id="username1span"></span></center>
           <center> <input type="text" style="width: 300px; height: 30px" class="form-control" id="username1" name="username" onblur="checkUsername1()" placeholder="Please input username"></center>
          </div>

          <div class="form-group">
            <center> <label for="name">name：</label><span id="namespan"></span></center>
             <center><input type="text" style="width: 300px; height: 30px" class="form-control" id="name" name="name" onblur="checkName()" placeholder="Please input name"></center>
          </div>

          <div class="form-group">
             <center><label for="age">age：</label><span id="agespan"></span></center>
             <center><input type="text" style="width: 300px; height: 30px" class="form-control" id="age" name="age" onblur="checkAge()" placeholder="Please input age"></center>
          </div>

          <div class="form-group">
             <center><label for="teleno">telenum：</label><span id="telenumspan"></span></center>
             <center><input type="text" style="width: 300px; height: 30px" class="form-control" id="teleno" name="teleno" onblur="checkTelenum()" placeholder="Please input telenum"></center>
          </div>

          <div class="form-group" style="text-align: center">
              <input class="btn btn-primary" type="submit" onClick="return confirm('确定添加?')" value="提交" />
              <input class="btn btn-default" type="reset" value="重置" />
              <input class="btn btn-default" type="button" value="返回" />
          </div>
      </form>
    </div>

    <div class="container">
        <center> <h2 style="color:#FFFBFF">数据库表users删除信息</h2> </center>
<%--        ${pageContext.request.contextPath}/delUserServlet--%>
        <form action="${pageContext.request.contextPath}/delUserServlet" onsubmit="return checkSubmit2()" method="post">
<%--            <input type="hidden" name="type" value="person">--%>
            <div class="form-group">
               <center><label for="username2">username：</label><span id="username2span"></span></center>
               <center><input type="text"  style="width: 300px; height: 30px" class="form-control" id="username2" name="username2" onblur="checkUsername2()" placeholder="Please input username"></center>
            </div>
            <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" onClick="return confirm('确定删除?')" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回" />
             </div>
        </form>

      <script type="text/javascript">
            /* 鼠标点击特效 */
            var a_idx = 0;
            jQuery(document).ready(function($) {
                $("body").click(function(e) {
                   // var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正" ,"法治", "爱国", "敬业", "诚信", "友善");
                    var a = new Array("继伟好帅","我好爱","加油加油","你最帅");
                    var $i = $("<span/>").text(a[a_idx]);
                    a_idx = (a_idx + 1) % a.length;
                    var x = e.pageX,
                        y = e.pageY;
                    $i.css({
                        "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
                        "top": y - 20,
                        "left": x,
                        "position": "absolute",
                        "font-weight": "bold",
                        "color": "#ff6651"
                    });
                    $("body").append($i);
                    $i.animate({
                            "top": y - 180,
                            "opacity": 0
                        },
                        1500,
                        function() {
                            $i.remove();
                        });
                });
            });
        </script>

    </div>
<%--    <center><h4><a href="<%=request.getContextPath()%>/listServlet">查看数据库数据</a></h4> </center>--%>
  </body>
</html>
