package Lab1;

import org.hamcrest.Condition;

import java.sql.SQLException;

public class Lab1 {
    DBConnector db=new DBConnector();
    OperatingUser opU=new OperatingUser();
    OperatingPerson opP=new OperatingPerson();
    public void clear()throws Exception{
        System.out.println();
        opP.clearPerson(db);
        opU.clearUsers(db);
        System.out.println();
    }
    public void add1()throws Exception{
        try{

            opU.addUser(new User("ly","123456"),db);
            opU.addUser(new User("liming","345678"),db);
            opU.addUser(new User("test","11111"),db);
            opU.addUser(new User("test1","12345"),db);
            //在表person中插入3行数据
            opP.addPerson(new Person("ly","雷力"),db);
            opP.addPerson(new Person("liming","李明",25),db);
            opP.addPerson(new Person("test","测试用户",20,"13388449933"),db);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void add2()throws Exception{
        try{
           opP.addPerson(new Person("ly","王五"),db);
           opP.addPerson(new Person("test2","测试用户2"),db);
           opP.addPerson(new Person("test1","测试用户1",33),db);
           opP.addPerson(new Person("test","张三",23,"18877009966"),db);
           opP.addPerson(new Person("admin","admin"),db);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete()throws Exception{
        opU.deleteUser("test",db);
    }
    public void find()throws Exception{
        if(opU.findUser("tes",db)){
            System.out.println("yes");
        }
        opP.findPerson("test2",db);
    }
    public void update()throws Exception{
        String[] s1={"test1","12345679"};
        //opU.updateUser(s1,db);
        String[] s2={"test2","","","13874353591"};
        opP.updatePerson(s2,db);
    }
    public void getP()throws Exception{
        Person p=opP.getPerson("test2",db);
        System.out.println(p.getAge());
        System.out.println(p.getName());
        System.out.println(p.getTeleno());
    }
    public static void main(String[] args)throws Exception {
        Lab1 l=new Lab1();
        //l.clear();
        //l.add1();
        //l.add2();
        //l.delete();
        l.find();
        //l.update();
        l.getP();
    }
}
        /*Users_Person us = new Users_Person();
        us.Connect();
        //DBConnector db=new DBConnector();
        //向users表中插入数据
        us.clear("usres");
        us.clear("person");
        String[] user1={"users","ly","123456"};
        String[] user2={"users","liming","345678"};
        String[] user3={"users","test","111111"};
        String[] user4={"users","test1","12345"};
        us.insert(user1);
        us.insert(user2);
        us.insert(user3);
        us.insert(user4);
        //向person表插入数据
        String[] person1={"person","ly","王五","",""};
        String[] person2={"person","liming","李明","25",""};
        String[] person3={"person","test","测试用户","20","13388449933"};
        us.insert(person1);
        us.insert(person2);
        us.insert(person3);
        //
        String[] p1={"person","ly","王五","",""};
        String[] p2={"person","test2","测试用户","1","33"};
        String[] p3={"person","test","张三","23","18877009966"};
        String[] p4={"admin","admin","","",""};
        us.insert(p1);
        us.insert(p3);
        us.insert(p4);
        //
        String[] u1={"users","test"};
        us.delete(u1);
        //us.insert(p2);
        String[] s = new String[5];
        String YESorNo = new String();
        int f=0;
        YESorNo = "y";
        int flag = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        Scanner input = new Scanner(System.in);
        try {
            while (YESorNo.equals("n")==false) {
                try {
                    if(flag>0){
                        System.out.print("是否继续操作n/y：");
                        YESorNo=br.readLine();
                        if(YESorNo.equals("n")){
                            us.Close();
                            break;
                        }
                    }
                    System.out.print("请选择要进行的操作：1：增加" +
                            "\t2：删除" +
                            "\t3：查找" +
                            "\t4：修改" +
                            "\t5：查看" +
                            "\t6：清空" +
                            "\n请输入选项：");
                    f=input.nextInt();
                    flag += 1;
                    System.out.print("请输入要操作的表：");
                    s[0] = br.readLine();
                    if(f==1){
                        if (s[0].equals("users")) {
                            System.out.print("请输入用户名：");
                            s[1] = br.readLine();
                            System.out.print("请输入密码：");
                            s[2] = br.readLine();
                            us.insert(s);
                            continue;
                        }
                        else if (s[0].equals("person")) {
                            System.out.print("请输入用户名：");
                            s[1] = br.readLine();
                            System.out.print("姓名：");
                            s[2] = br.readLine();
                            System.out.print("年龄：");
                            s[3] = br.readLine();
                            if (s[3].equals("")) {
                                us.insert(s);
                                continue;
                            }
                            System.out.print("电话号码：");
                            s[4] = br.readLine();
                            if (s[4].equals("")) {
                                us.insert(s);
                                continue;
                            }
                            us.insert(s);
                            continue;
                        }
                        else {
                            System.out.println("表名输入错误！");
                        }
                    }
                    else if(f==2){
                        if(s[0].equals("users")){
                            System.out.print("请输入用户名：");
                            s[1] = br.readLine();
                            us.delete(s);
                        }
                        else if(s[0].equals("person")){
                            System.out.print("请输入用户名：");
                            s[1] = br.readLine();
                            us.delete(s);
                        }
                        else{
                            System.out.println("表名输入错误！");
                        }
                    }
                    else if(f==3){
                        System.out.print("请输入要查找的用户：");
                        s[1]=br.readLine();
                        us.find(s);
                    }
                    else if(f==4){
                        if(s[0].equals("users")){
                            System.out.print("请输入要修改密码的用户名:");
                            s[1]=br.readLine();
                            System.out.print("请输入新的密码:");
                            s[2]=br.readLine();
                        }
                        else if(s[0].equals("person")){
                            System.out.print("请输入要修改密码的用户名:");
                            s[1]=br.readLine();
                            System.out.print("请输入新的姓名（如果不修改直接回车即可）:");
                            s[2]=br.readLine();
                            System.out.print("请输入新的年龄（如果不修改直接回车即可）:");
                            s[3]=br.readLine();
                            System.out.print("请输入新的电话号码（如果不修改直接回车即可）:");
                            s[4]=br.readLine();
                        }
                        us.update(s);
                    }
                    else if(f==5){
                        us.showSelect(s[0]);
                    }
                    else if(f==6){
                        us.clear(s[0]);
                    }
                    else {
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */