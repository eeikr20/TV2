package Domain;

public class Search {
    public void viewCast(){
        DBMS.pgSQL.viewCast();
    }

    public void viewPrograms(){
        DBMS.pgSQL.viewPrograms();
    }
    public void viewProgramCredits(){
        System.out.println("What program do you want to view?");
        String program = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from program where name = '" + program + "'")==0){
            System.out.println("That program does not exist");
            return;
        }
        int id = DBMS.pgSQL.getID("select id from program where name = '" + program +"'");

        DBMS.pgSQL.viewProgramCredits("select * from credit where program = " + id);

        //todo GUI
    }

    public void viewCastCredits(){
        System.out.println("What cast do you want to view?");
        String cast = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from casts where name = '" + cast + "'")==0){
            System.out.println("That cast does not exist");
            return;
        }
        int id = DBMS.pgSQL.getID("select id from casts where name = '" + cast +"'");

        DBMS.pgSQL.viewCastCredits("select * from credit where castid = " + id);

        //todo GUI
    }
}
