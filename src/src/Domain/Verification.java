package Domain;
//import Database.PGSQL;

import Controller.MainFX;

public class Verification {

    private boolean checkAccess(String name){
        if(DBMS.pgSQL.sqlContains("select count(*) from users where name = '" + name + "'")==0){
            System.out.println("That user does nor exist");
            return false;
        }
        String foundType = DBMS.pgSQL.getPassword("select type from users where name = '" + name + "'");
        if(!foundType.equals("producer") && !foundType.equals("administrator")){
            System.out.println("That user should not be able to create programs");
            return false;
        }
        return true;
    }
    public void verifyCredit(int programID, int castID, String role){
        DBMS.pgSQL.verifySQL.verifyCredit(programID, castID, role);
    }
    public void verifyCast(String producerName, String castName){
        //System.out.println("Who is the producer that created the cast?");
        //String producerName = DBMS.scanner.nextLine();

        if(!checkAccess(producerName)){
            return;
        }

        //System.out.println("what is the name of the cast?");
        //String castName= DBMS.scanner.nextLine();

        if(DBMS.pgSQL.getVerification("select verified from casts where name = '" + castName +"'")){
            System.out.println("That cast has already been verified");
            return;
        }

        int producerID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + producerName + "'");

        if(DBMS.pgSQL.getID("SELECT owner FROM casts where  name = '" + castName + "'") != producerID){
            System.out.println("That is not the correct producer");
            return;
        }

        DBMS.pgSQL.query("UPDATE casts SET verified=TRUE WHERE name = '" + castName + "'");
        MainFX.db.notification.addUpdate("The cast: " + castName + " has been verified and added.", producerID);
    }

    public void verifyProgram(String producerName, String programName){
        //System.out.println("Who is the producer of this document?");
        //String producerName = DBMS.scanner.nextLine();

        if(!checkAccess(producerName)){
            return;
        }

        //System.out.println("What is the name of the program?");
        //String programName = DBMS.scanner.nextLine();
        //
        if(DBMS.pgSQL.getVerification("select verified from program where name = '" + programName +"'")){
            System.out.println("That program has already been verified");
            return;
        }

        int producerID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + producerName + "'");

        if(DBMS.pgSQL.getID("SELECT owner FROM program where  name = '" + programName + "'") != producerID){
            System.out.println("That is not the correct producer");
            return;
        }

        //Print the roles of the program so that the admin can verify them

        //System.out.println("Are you sure the program is correct?");
        //String answer = DBMS.scanner.nextLine();

        DBMS.pgSQL.query("UPDATE program SET verified=TRUE WHERE name = '" + programName + "'");
        MainFX.db.notification.addUpdate("The program: " + programName + " has been verified and added.", producerID);

    }

}
