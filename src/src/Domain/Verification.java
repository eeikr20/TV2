package Domain;

import Controller.MainFX;
import Database.PGSQL;

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
    public void verifyCast(String producerName, int id){
        if(!checkAccess(producerName)){
            System.out.println("no access");
            return;
        }
        int producerID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + producerName + "'");
        PGSQL.query("UPDATE casts SET verified=TRUE WHERE id = " + id);
        MainFX.db.notification.addUpdate("The cast with id: " + id + " has been verified and added.", producerID);
    }

    public void verifyProgram(String producerName, String programName){
        if(!checkAccess(producerName)){
            return;
        }
        if(DBMS.pgSQL.getVerification("select verified from program where name = '" + programName +"'")){
            System.out.println("That program has already been verified");
            return;
        }

        int producerID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + producerName + "'");

        if(DBMS.pgSQL.getID("SELECT owner FROM program where  name = '" + programName + "'") != producerID){
            System.out.println("That is not the correct producer");
            return;
        }

        PGSQL.query("UPDATE program SET verified=TRUE WHERE name = '" + programName + "'");
        MainFX.db.notification.addUpdate("The program: " + programName + " has been verified and added.", producerID);

    }

}
