package Domain;

public class Verification {
    /*
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
        //todo update to use database
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
        //todo update to use database
    }
    */
}
