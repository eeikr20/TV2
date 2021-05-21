package Domain;

public class Customer {
    String name;
    String password;
    int id;
    String type;

    Login login = new Login();
    Search search = new Search();
    Verification verification = new Verification();
    Crediting crediting = new Crediting();
    Notification notification = new Notification();

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
            case "administrator" ->adminCommand(input);
            case "account" ->accountCommand(input);
            case "producer" ->producerCommand(input);
            default -> System.out.println("something is wrong in runCommand");
        }
    }

    private void producerCommand(String input) {
        switch (input){
            case "add program" -> crediting.addProgram();
            case "read updates" -> notification.readUpdates(id);
            case "delete updates" -> notification.eraseUpdates(id);
            case "create cast" -> crediting.createCast();
            case "add cast" -> crediting.addCast();
            default -> accountCommand(input);
        }
    }

    public void userCommand(String input){
        switch (input){
            case "view programs" -> search.viewPrograms();
            case "view casts" -> search.viewCast();
            case "view cast roles" -> search.viewCastCredits();
            case "view program credits" -> search.viewProgramCredits();
            default -> System.out.println("invalid input");
        }
    }

    private void adminCommand(String input){
        switch (input){
            case "verify program" -> verification.verifyProgram();
            case "create super user" -> login.createSuperUser();
            case "verify cast" -> verification.verifyCast();
            default -> producerCommand(input);
        }
    }
    private void visitorCommand(String input){
        switch (input){
            case "login" -> login.login();
            case "signup" -> login.signUp();
            default -> userCommand(input);
        }
    }
    private void accountCommand(String input){
        switch (input){
            case "logout" -> login.logout();
            case "add to favorite" -> System.out.println("not implemented");
            default -> userCommand(input);
        }
    }
}
