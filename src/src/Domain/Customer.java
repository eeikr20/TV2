package Domain;

public class Customer {
    String name;
    String password;
    int id;
    String type;
    Login l = new Login();
    Search s = new Search();

    Customer(String name, String password,  int id, String type){
        this.name = name;
        this.password = password;
        this.id = id;
        this.type = type;
    }
    public void resetCustomer(String name, String password,  int id, String type){
        this.name = name;
        this.password = password;
        this.id = id;
        this.type = type;
    }
    public void runCommand(String input){
        switch (type){
            case "visitor" -> visitorCommand(input);
            case "admin" ->adminCommand(input);
            case "account" ->accountCommand(input);
            default -> System.out.println("something is wrong in runCommand");
        }
    }
    public void userCommand(String input){
        switch (input){
            case "view programs" -> s.viewPrograms();
            case "view casts" ->s.viewCast();
            case "view cast roles" ->s.viewCastCredits();
            case "view program credits" -> s.viewProgramCredits();
            default -> System.out.println("invalid input");
        }
    }

    private void adminCommand(String input){

    }
    private void visitorCommand(String input){
        switch (input){
            case "login" ->l.login();
            case "signup" ->l.signUp();
            default -> userCommand(input);
        }
    }
    private void accountCommand(String input){
        switch (input){
            case "logout" -> l.logout();
            case "add to favorite" -> System.out.println("not implemented");
            default -> userCommand(input);
        }
    }
}
