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
            default -> super.runCommand(input);
        }
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

    }
}
