package Domain;

public class Search {
    public void viewCast(){
        DBMS.postgresDB.viewCast();
    }

    public void viewPrograms(){
        DBMS.postgresDB.viewPrograms();
    }
    public void viewProgramCredits(){
        System.out.println("What program do you want to view?");
        String program = DB.scanner.nextLine();
        if(DBMS.postgresDB.sqlContains("select count(*) from program where name = '" + program + "'")==0){
            System.out.println("That program does not exist");
            return;
        }
        int id = DBMS.postgresDB.getID("select id from program where name = '" + program +"'");

        DBMS.postgresDB.viewProgramCredits("select * from credit where program = " + id);

        //todo GUI
    }

    public void viewCastCredits(){
        System.out.println("What cast do you want to view?");
        String cast = DB.scanner.nextLine();
        if(DBMS.postgresDB.sqlContains("select count(*) from casts where name = '" + cast + "'")==0){
            System.out.println("That cast does not exist");
            return;
        }
        int id = DBMS.postgresDB.getID("select id from casts where name = '" + cast +"'");

        DBMS.postgresDB.viewCastCredits("select * from credit where castid = " + id);

        //todo GUI
    }
}
