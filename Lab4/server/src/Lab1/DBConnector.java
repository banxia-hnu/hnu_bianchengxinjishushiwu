package Lab1;

import java.sql.*;

public class DBConnector {
    private Connection con = null; // 数据库连接对象
    private String URL = PropertyUtil.getValue("url");// 数据库连接地址
    private String UserName = PropertyUtil.getValue("user");// 数据库的用户名
    private String Password = PropertyUtil.getValue("password");// 数据库的密码
    private String DriverClass = PropertyUtil.getValue("driverClass"); //驱动名称
    private ResultSet res;
    private Statement stt;

    public DBConnector() {
        con = getConnection();
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DriverClass); // 加载驱动
            System.out.println("加载驱动成功!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //通过DriverManager类的getConenction方法指定三个参数,连接数据库
            conn = DriverManager.getConnection(URL, UserName, Password);
            System.out.println("连接数据库成功!!!");
            //返回连接对象
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void executeUpdateInsert(String table, String sql, String username) throws SQLException {
        String pass = "888888";
        String Sql = "select * from users where username = '" + username + "' ;";
        if (con == null)
            return;
        stt = con.createStatement();
        if (table.equals("person")) {
            try {
                res = stt.executeQuery(Sql);
                if (!res.next()) {//如果username不存在则插入并设置密码为888888
                    Sql = "insert into users values('" + username + "','" + pass + "');";
                    stt.executeUpdate(Sql);
                    System.out.print("users表中不存在用户" + username + "。已添加！");
                    System.out.println();
                }
                stt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("向person表添加数据成功！");
        } else if (table.equals("users")) {
            try {
                stt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.print("向users表添加数据成功！");
        }
    }

    public void executeUpdateDelete(String table, String username) throws SQLException {
        if (con == null) {
            return;
        }
        try {
            String Sql;
            stt = con.createStatement();
            if (table.equals("person")) {
                Sql = "select * from users where username = '" + username + "' ;";
                res = stt.executeQuery(Sql);
                if (!res.next()) {
                    System.out.println("users表中没有用户" + username);
                } else {
                    Sql = "delete from users WHERE username='" + username + "';";
                    stt.executeUpdate(Sql);
                    System.out.println("在users表删除用户" + username + "成功！");
                }
                Sql = "DELETE FROM person WHERE username='" + username + "';";
                stt.executeUpdate(Sql);
                System.out.println("在person表删除用户" + username + "成功！");
            } else if (table.equals("users")) {
                Sql = "select * from person where username = '" + username + "' ;";
                res = stt.executeQuery(Sql);
                if (!res.next()) {
                    System.out.println("person表中没有用户" + username);
                } else {
                    Sql = "delete from person WHERE username='" + username + "';";
                    stt.executeUpdate(Sql);
                    System.out.println("在person表删除用户" + username + "成功！");
                    showSelect("person");
                }
                Sql = "DELETE FROM users WHERE username='" + username + "';";
                stt.executeUpdate(Sql);
                System.out.println("在users表删除用户" + username + "成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showSelect(String table) throws SQLException {
        try {
            String sql = new String();
            stt = con.createStatement();
            if (table.equals("person")) {
                sql = "select * from person";
                res = stt.executeQuery(sql);
                // 获取数据
                //System.out.print("\n");
                System.out.println("表 person");
                System.out.println("username            name            age             teleno");
                while (res.next()) {
                    System.out.printf("%-15s" + "\t\t" + "%-8s" + "\t\t", res.getString(1), res.getString(2));
                    if (res.getInt(3) == 0) {
                        // System.out.printf("%");
                        System.out.println("\t\t\t\t" + res.getString(4));
                        continue;
                    }
                    System.out.printf("%-9d", res.getInt(3));
                    System.out.println("\t\t" + res.getString(4));
                }
            }
            if (table.equals("users")) {
                sql = "select * from users";
                res = stt.executeQuery(sql);
                System.out.print("\n");
                System.out.println("表 users");
                System.out.println("username            pass");
                // 获取数
                while (res.next()) {
                    System.out.printf("%-15s" + "\t\t" + "%-25s" + "\t\t\n", res.getString(1), res.getString(2));
                }
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean find(String table, String username) throws SQLException {
        boolean isExist=false;
        try {
            String sql = new String();
            stt = con.createStatement();
            if (table.equals("person")) {
                sql = "select * from person where username = '" + username + "' ;";
                res = stt.executeQuery(sql);
                if (!res.next()) {
                    System.out.println("person表中没有用户" + username);
                } else {
                    isExist=true;
                    System.out.print("用户名:" + res.getString(1) + "\t姓名:"
                            + res.getString(2));
                    if (res.getInt(3) == 0) {
                        // System.out.printf("%");
                        System.out.println("\t年龄：    \t电话号码：" + res.getString(4));
                    } else {
                        System.out.print("\t年龄：" + res.getInt(3));
                        System.out.println("\t电话号码：" + res.getString(4));
                    }
                }
            }
            if (table.equals("users")) {
                sql = "select * from users where username = '" + username + "' ;";
                res = stt.executeQuery(sql);
                if (!res.next()) {
                    System.out.println("users表中没有用户" + username);
                } else {
                    isExist=true;
                    System.out.println("用户名:" + res.getString(1) + "\t密码:"
                            + res.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    public void update(String table, User u) throws SQLException {
        try {
            String sql = new String();
            stt = con.createStatement();
            /*if (table.equals("person")) {
                if (s[1].equals("") == false) {
                    sql = "UPDATE person SET name = '" + s[1] + "' WHERE username = '" + s[0] + "';";
                    PreparedStatement ps = con.prepareStatement(sql);
                    //执行sql语句
                    ps.executeUpdate();
                }
                if (s[2].equals("") == false) {
                    int age = Integer.parseInt(s[2]);
                    sql = "UPDATE person SET age = " + age + " WHERE username = '" + s[0] + "';";
                    PreparedStatement ps = con.prepareStatement(sql);
                    //执行sql语句
                    ps.executeUpdate();
                }
                if (s[3].equals("") == false) {
                    sql = "UPDATE person SET teleno = '" + s[3] + "' WHERE username = '" + s[0] + "';";
                    PreparedStatement ps = con.prepareStatement(sql);
                    //执行sql语句
                    ps.executeUpdate();
                }
                System.out.print("修改用户信息成功！");
            }*/
            if (table.equals("users")) {
                sql = "UPDATE users SET pass = '" + u.getPassword() + "' WHERE username = '" + u.getUsername() + "';";
                PreparedStatement ps = con.prepareStatement(sql);
                //执行sql语句
                ps.executeUpdate();
                System.out.print("修改密码成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(String table) {
        try {
            String sql = new String();
            stt = con.createStatement();
            if (table.equals("person")) {
                sql = "truncate table person";
                stt.executeUpdate(sql);
                System.out.println("清空person表中数据成功！");
            } else {
                sql = "truncate table users";
                stt.executeUpdate(sql);
                System.out.println("清空users表中数据成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {  //close
        if (this.con != null) {
            try {
                this.con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet showUsers(String sql) throws SQLException {
        stt = con.createStatement();
        res = stt.executeQuery(sql);
        return res;
    }

    public ResultSet showPerson(String sql) throws SQLException {
        res = stt.executeQuery(sql);
        stt = con.createStatement();
        return res;
    }

    public int checkPassword(User u) throws SQLException {
        boolean hasUser = false;
        boolean rightPass = false;
        try {
            String sql = new String();
            stt = con.createStatement();
            sql = "select * from users where username = '" + u.getUsername() + "' ;";
            res = stt.executeQuery(sql);
            if (!res.next()) {
                hasUser=false;
            } else {
                hasUser=true;
                if (u.getPassword().equals(res.getString(2))) {
                    System.out.println(res.getString(2));
                    rightPass=true;
                } else {
                   rightPass=false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!hasUser) return -1;//无该用户
        else if(!rightPass) return 0;//有该用户，但是密码输入错误
        return 1;//有该用户，且密码输入正确
    }
    public int checkTelephoneNumber(Person p) throws SQLException {
        boolean hasUser = false;
        boolean rightTele = false;
        try {
            String sql = new String();
            stt = con.createStatement();
            sql = "select * from person where username = '" + p.getUsername() + "' ;";
            res = stt.executeQuery(sql);
            if (!res.next()) {
                hasUser=false;
            } else {
                if (p.getTeleno().equals(res.getString(4))) {
                    rightTele=true;
                } else {
                    hasUser=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rightTele) return 1;//电话正确
        return 0;//电话错误
    }

    public Person getPerson(String username)throws SQLException{
        Person p=new Person(username,"");
        try{
            String sql=new String();
            stt=con.createStatement();
            sql = "select * from person where username = '" + username + "' ;";
            res = stt.executeQuery(sql);
            if (!res.next()) {
                int i=0;
            } else {
                p.setName(res.getString(2));
                System.out.println(res.getString(2));
                p.setAge(res.getInt(3));
                p.setTeleno(res.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }
}