package Domain;

public class Login {
    public void login(){
        System.out.println("Please enter username");
        String name = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from users where name = '" + name + "'")==0){
            System.out.println("User does not exist");
            return;
        }
        System.out.println("Please enter password");
        String password = DBMS.scanner.nextLine();
        String foundPassword = DBMS.pgSQL.getPassword("select password from users where name = '" + name + "'");
        if(foundPassword.equals(password)){
            //int id = DBMS.postgresDB.getID("select id from usere where name = '" + name + "'");
            //String type = DBMS.postgresDB.getPassword("select type from usere where name = '" + name + "'");
            //DBMS.currentCustomer.resetCustomer(name, password, id, type);
            String[] userData = DBMS.pgSQL.setUserData(name);
            DBMS.currentCustomer.resetCustomer(userData[0], userData[1], Integer.valueOf(userData[2]), userData[3]);
        }
        /*
        if (((Account)DB.users.get(name)).getPassword().equals(password)){
            //Domain.DBMS.currentUser = new Domain.Account("account",name,password);
            DBMS.currentUser = DB.users.get(name);
        }
*/
        //todo get from database
    }

    public void signUp(){
        System.out.println("Please enter username");
        String name = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from users where name = '" + name + "'")!=0){
            System.out.println("User already exist");
        }
        else{
            System.out.println("Please enter a password");
            String password = DBMS.scanner.nextLine();
            //Account account = new Account("account", name, password);
            //DB.users.put(name,account);

            //DB.count = DB.count + 1;
            DBMS.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'account');");
        }
        //todo remove db usage
    }
    public void logout(){
        //todo remove old db
        //DBMS.currentUser = new Visitor();
        DBMS.currentCustomer.resetCustomer("", "", -1, "visitor");
    }
    public void createSuperUser(){
        System.out.println("What is the name of the user?");
        String name = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from users where name = '" + name + "'")!=0){
            System.out.println("That user is already taken");
            return;
        }
        System.out.println("What is the users password");
        String password = DBMS.scanner.nextLine();
        System.out.println("What user type are you creating?");
        //User user= null;
        String type = DBMS.scanner.nextLine();
        /*
        switch (type){
            case "producer" -> user= new Producer(type, name, password);
            case "administrator"->user =new Administrator(type, name, password);
            case "account"->user= new Account(type, name, password);
            default -> System.out.println("That is not a valid type");
        }
        if(user!=null){
            DB.users.put(name, user);
        }
        */
        DBMS.pgSQL.query("INSERT INTO users VALUES ('" + name + "','" + password + "', DEFAULT,'" + type + "');");
        //todo check for existing users in the db
    }
}
