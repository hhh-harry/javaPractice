package MyPracticeCode;

public class TestRole {
    public static void main(String[] args) {
        Role r1 = new Role("xian", 100, '男');
        Role r2 = new Role("shun", 100, '女');

        r1.showRoleInfo();
        r2.showRoleInfo();


        while(true){
            r2.attack(r1);
            if(r1.getBlood()==0){
                System.out.println(r2.getName()+"K.O了"+r1.getName());
                break;
            }


            r1.attack(r2);
            if(r2.getBlood()==0){
                System.out.println(r1.getName()+"K.O了"+r2.getName());
                break;
            }

        }




    }
}
