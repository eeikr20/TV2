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
    public boolean userInDB(String name){
        if(DBMS.pgSQL.loginSQL.userInDB(name)){
            return true;
        }
        return false;
    }

    public void signUp(String name, String password){
        MainFX.db.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'account');");
    }
    public void logout(){
        MainFX.db.currentCustomer.resetCustomer("", "", -1, "visitor");
    }


    public void createSuperUser(String name, String password, String type){
        if(DBMS.pgSQL.loginSQL.userInDB(name)){
            System.out.println("User already exist");
        }
        MainFX.db.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'" + type + "');");
    }
}
