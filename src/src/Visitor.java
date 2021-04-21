public class Visitor extends User{

    public Visitor(){
        super("visitor");
    }

    public void runCommand(String input){
        switch (input){
            case "login" ->login();
            case "signup" ->signUp();
            default -> super.runCommand(input);
        }

    }

    public void login(){
        System.out.println("Please enter username");
        String name = DB.scanner.nextLine();
        if (!DB.users.containsKey(name)){
            System.out.println("User does not exist");
            return;

        }
        System.out.println("Please enter password");
        String password = DB.scanner.nextLine();
        if (((Account)DB.users.get(name)).getPassword().equals(password)){
            //DBMS.currentUser = new Account("account",name,password);
            DBMS.currentUser = DB.users.get(name);
        }

    }

    public void signUp(){
        System.out.println("Please enter username");
        String name = DB.scanner.nextLine();
        if (DB.users.containsKey(name)){
            System.out.println("User already exist");
        }
        else{
            System.out.println("Please enter a password");
            String password = DB.scanner.nextLine();
            Account account = new Account("account", name, password);
            DB.users.put(name,account);
        }
    }


}
