package Domain;

public class Administrator extends Producer {
    //Attributes
    private String name;
    private int id;


    public Administrator(String type, String name, String password){
        super(type, name, password);
    }

    //Methods
    public void runCommand(String input){
        switch (input){
            case "verify program" ->verifyProgram();
            case "create super user" ->createSuperUser();
            case "verify cast" -> verifyCast();
            default -> super.runCommand(input);
        }
    }
    public void verifyCast(){
        System.out.println("Who is the producer that created the cast?");
        String name = DB.scanner.nextLine();

        if (!DB.users.containsKey(name)) {
            System.err.println("That producer does not exist.");
            return;
        }
        if (!DB.users.get(name).getType().equals("producer")) {
            System.err.println("That producer does not exist.");
            return;
        }
        System.out.println("what is the name of the cast?");
        String cast= DB.scanner.nextLine();

        if (!((Producer)DB.users.get(name)).getTempCast().containsKey(cast)){
            System.out.println("That producer has not created a cast member with that name");
            return;
        }
        Cast c = ((Producer)DB.users.get(name)).getTempCast().get(cast);
        DB.casts.put(cast, c);
        ((Producer)DB.users.get(name)).getTempCast().remove(cast);
        ((Producer)DB.users.get(name)).addUpdate("The cast: " + cast + " has been verified and added.");
    }
    public void verifyProgram(){
        System.out.println("Who is the producer of this document?");
        String name = DB.scanner.nextLine();

        if (!DB.users.containsKey(name)) {
            System.err.println("That producer does not exist.");
            return;
        }
        if (!DB.users.get(name).getType().equals("producer")) {
            System.err.println("That producer does not exist.");
            return;
        }

        System.out.println("What is the name of the program?");
        String programName = DB.scanner.nextLine();

        if (!((Producer)DB.users.get(name)).getTempOwner().containsKey(programName)) {
            System.err.println("That producer does not have a program with that name.");
            return;
        }

        //Print the roles of the program so that the admin can verify them

        System.out.println("Are you sure the program is correct?");
        String answer = DB.scanner.nextLine();

        if (answer.equals("yes")) {
            Program p = ((Producer) DB.users.get(name)).getTempOwner().get(programName); // placeholder name
            ((Producer) DB.users.get(name)).addOwner(programName, p); //adds to owner list
            DB.programs.put(programName, p);//adds to general database
            ((Producer) DB.users.get(name)).removeFromTempOwner(programName); //Removes from tempOwner list
            ((Producer) DB.users.get(name)).addUpdate("The document: " + programName + " has been verified and added.");
        }


    }

    private void createSuperUser(){
        System.out.println("What is the name of the user?");
        String name = DB.scanner.nextLine();
        if (DB.users.containsKey(name)){
            System.out.println("That user is already taken");
            return;
        }
        System.out.println("What is the users password");
        String password = DB.scanner.nextLine();
        System.out.println("What user type are you creating?");
        User user= null;
        String type = DB.scanner.nextLine();
        switch (type){
            case "producer" -> user= new Producer(type, name, password);
            case "administrator"->user =new Administrator(type, name, password);
            case "account"->user= new Account(type, name, password);
            default -> System.out.println("That is not a valid type");
        }
        if(user!=null){
            DB.users.put(name, user);
        }
    }
}
