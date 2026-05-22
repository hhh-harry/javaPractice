package studentsystem01;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class APP {
     static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作");
            System.out.println("1：登录");
            System.out.println("2：注册");
            System.out.println("3：忘记密码");
            System.out.println("4：退出");
            String choose = sc.next();
            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("谢谢使用");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");
            }
        }
    }

    //忘记密码
    public static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (flag) {
            int index = findindex(list, username);
            User user = list.get(index);
            System.out.println("请输入身份证号码");
            String personID = sc.next();
            System.out.println("请输入手机号码");
            String phonenumber = sc.next();
            if (!(user.getPersonid().equals(personID) && user.getPhonenumber().equals(phonenumber))) {
                System.out.println("身份证号码或者手机号码不一致，无法修改");
                return;
            }
            while (true) {
                System.out.println("请输入修改密码");
                String password = sc.next();
                System.out.println("请再次输入修改密码");
                String againPassword = sc.next();
                if (password.equals(againPassword)) {
                    user.setPassword(password);
                    System.out.println("密码修改成功");
                    break;
                }
            }
        } else {
            System.out.println("用户名未注册，请先注册");
        }
    }

    //找到用户名在list容器中的位置
    public static int findindex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    //登录
    public static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String username = sc.next();
            if (!contains(list, username)) {
                System.out.println("用户名未注册，请先注册");
                return;
            }

            System.out.println("请输入密码");
            String password = sc.next();

            //保证验证码输入正确
            while (true) {
                String rightcode = getCode();
                System.out.println("正确的验证码为：" + rightcode);
                System.out.println("请输入验证码");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightcode)) {
                    System.out.println("验证码正确");
                    break;
                } else {
                    System.out.println("验证码错误，请重新输入");
                }
            }

            //验证用户名和密码是否正确
            //即判断集合中是否用户名和密码
            User user = new User(username, password, null, null);
            boolean flag = checkUserInfo(list, user);
            if (flag) {
                System.out.println("登录成功");
                StudentSystem ss = new StudentSystem();
                ss.StartStudentSystem();
                return;
            } else {
                if (i == 2) {
                    System.out.println("当前用户已经被锁定，请联系大帅哥：XXX-XXXXXX");
                    return;
                }
                System.out.println("登录失败,账户或者密码错误，你还剩下" + (2 - i) + "次机会");
            }
        }


    }

    //判断用户名和密码是否存在
    public static boolean checkUserInfo(ArrayList<User> list, User user) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    //注册
    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        String name;
        String password;
        String personid;
        String phonenumber;
        //用户名的校验
        while (true) {
            System.out.println("请输入用户名");
            name = sc.next();
            boolean flat1 = checkUserName(name);
            if (!flat1) {
                System.out.println("用户名输入格式错误，请重新输入");
                continue;
            }

            boolean flat2 = contains(list, name);
            if (flat2) {
                System.out.println("当前用户名已重复，请重新输入");
            } else {
                System.out.println("录入成功");
                break;
            }
        }
        //密码录入两次，一致就可以录入
        while (true) {
            System.out.println("请输入注册密码");
            password = sc.next();
            System.out.println("请再次输入注册密码");
            String againpassword = sc.next();
            if (password.equals(againpassword)) {
                System.out.println("两次输入一致，录入成功");
                break;
            } else {
                System.out.println("请重新输入密码");
            }
        }
        //身份证号码检验
        while (true) {
            System.out.println("请输入身份证ID");
            personid = sc.next();
            boolean flat = checkPersonId(personid);
            if (!flat) {
                System.out.println("请重新输入");
            } else {
                System.out.println("录入成功");
                break;
            }
        }
        //手机号码的录入
        while (true) {
            System.out.println("请输入要录入的手机号码");
            phonenumber = sc.next();
            boolean flat = checkPhoneNumber(phonenumber);
            if (!flat) {
                System.out.println("录入的手机号码错误，重新输入");
            } else {
                System.out.println("录入手机号码成功");
                break;
            }
        }
        //创建对象录入list集合里
        User u = new User(name, password, personid, phonenumber);
        list.add(u);
        System.out.println("注册成功");
        printList(list);

    }

    //遍历集合list方法
    public static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            System.out.println(u.getUsername() + "," + u.getPassword() + "," + u.getPersonid() + "," + u.getPhonenumber());
        }
    }

    //判断name在不在list集合中
    public static boolean contains(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            String oldname = u.getUsername();
            if (oldname.equals(name)) {
                return true;
            }
        }
        return false;
    }

    //判断用户名是否正确
    public static boolean checkUserName(String name) {
        //        用户名长度必须在3~15位之间
        if (name.length() < 3 || name.length() > 15) return false;
//        只能是字母加数字的组合，但是不能是纯数字
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return true;
            }
        }
        return false;
    }

    //判断身份证号码输入是否正确
    public static boolean checkPersonId(String personid) {
        //长度为18
        if (personid.length() != 18) {
            return false;
        }
        //不能以0开头
        char start = personid.charAt(0);
        if (start == '0') {
            return false;
        }
        //前17位都是数字
        for (int i = 0; i < personid.length() - 1; i++) {
            char c = personid.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        //最后一位可以是数字，也可以是大写X或者小写x
        char c = personid.charAt(personid.length() - 1);
        if ((c >= '0' && c <= '9') || (c == 'x') || (c == 'X')) {
            return true;
        } else {
            return false;
        }
    }

    //判断手机号是否正确
    public static boolean checkPhoneNumber(String phonenumber) {
        if (phonenumber.length() != 11) {
            return false;
        }
        if (phonenumber.charAt(0) == '0') {
            return false;
        }
        for (int i = 0; i < phonenumber.length(); i++) {
            char c = phonenumber.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    //生成一个验证码
    public static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) (i + 'a'));
            list.add((char) (i + 'A'));
        }
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            sb.append(list.get(index));
        }
        int randomnumber = r.nextInt(10);
        sb.append(randomnumber);

        //让数字出现在随机的位置
        char[] arr = sb.toString().toCharArray();
        int index = r.nextInt(arr.length);
        char temp = arr[index];
        arr[index] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        return new String(arr);
    }
}
    
