package Domain;

public class Login {
    public void login(){
        System.out.println("Please enter username");
        String name = DB.scanner.nextLine();
        if (!DB.users.containsKey(name)){
            System.out.println("Domain.User does not exist");
            return;

        }
        System.out.println("Please enter password");
        String password = DB.scanner.nextLine();
        if (((Account)DB.users.get(name)).getPassword().equals(password)){
            //Domain.DBMS.currentUser = new Domain.Account("account",name,password);
            DBMS.currentUser = DB.users.get(name);
        }
        //todo get from database
        String[] userData = DBMS.postgresDB.setUserData(name);
        DBMS.currentCustomer.resetCustomer(userData[0], userData[1], Integer.valueOf(userData[2]), userData[3]);
    }

    public void signUp(){
        System.out.println("Please enter username");
        String name = DB.scanner.nextLine();
        if (DB.users.containsKey(name)){
            System.out.println("Domain.User already exist");
        }
        else{
            System.out.println("Please enter a password");
            String password = DB.scanner.nextLine();
            Account account = new Account("account", name, password);
            DB.users.put(name,account);

            //DB.count = DB.count + 1;
            DBMS.postgresDB.query("INSERT INTO users VALUES ('" + name + "','" + password + "'," + DB.count + ",'account');");
        }
        //todo remove db usage
    }
    public void logout(){
        //todo remove old db
        DBMS.currentUser = new Visitor();
        DBMS.currentCustomer.resetCustomer("", "", 0, "visitor");
    }
}
