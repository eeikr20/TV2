package Domain;

import Controller.MainFX;

public class Login {
    public int login(String name, String password){
        if(!DBMS.pgSQL.loginSQL.userInDB(name)){
            System.out.println("User does not exist");
            return -1;
        }
        String foundPassword = DBMS.pgSQL.loginSQL.getPassword(name);
        if(foundPassword.equals(password)){
            String[] userData = DBMS.pgSQL.loginSQL.setUserData(name);
            MainFX.db.currentCustomer.resetCustomer(userData[0], userData[1], Integer.parseInt(userData[2]), userData[3]);
        }
        else
            return -2;
        return 1;
    }

    public int signUp(String name, String password){
        if(DBMS.pgSQL.loginSQL.userInDB(name)){
            System.out.println("User already exist");
            return -1;
        }
        else{
            MainFX.db.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'account');");
        }
        return 1;
    }
    public void logout(){
        MainFX.db.currentCustomer.resetCustomer("", "", -1, "visitor");
    }


    public void createSuperUser(String name, String password, String type){
//        System.out.println("What is the name of the user?");
//        String name = DBMS.scanner.nextLine();
//        if(DBMS.pgSQL.loginSQL.userInDB(name)){
//            //System.out.println("That user is already taken");
//            return -1;
//        }

//        System.out.println("What is the users password");
//        String password = DBMS.scanner.nextLine();

//        System.out.println("What user type are you creating?");
//        String type = DBMS.scanner.nextLine();

        MainFX.db.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'" + type + "');");
        //return 1;
        //todo check for existing users in the db
    }
}
