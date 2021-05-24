package Domain;

import Controller.MainFX;

public class Search {
    public String[] viewAllCast(){
        return DBMS.pgSQL.searchSQL.viewAllCast();
    }

    public String[] viewAllPrograms(){
        return DBMS.pgSQL.searchSQL.viewAllPrograms();
    }
    public String[] viewCast(String input){
        return DBMS.pgSQL.searchSQL.viewCast(input);
    }
    public String[] viewPrograms(String input){
        return DBMS.pgSQL.searchSQL.viewPrograms(input);
    }

    public void viewProgramCredits(String program){
        //System.out.println("What program do you want to view?");
        //String program = DBMS.scanner.nextLine();
        if(DBMS.pgSQL.sqlContains("select count(*) from program where name = '" + program + "'")==0){
            System.out.println("That program does not exist");
            return;
        }
        int id = DBMS.pgSQL.getID("select id from program where name = '" + program +"'");


        String[] data = DBMS.pgSQL.searchSQL.viewProgramCredits(id);
        MainFX.db.currentProgram.setProgram(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2]), Boolean.valueOf(data[3]), Integer.valueOf(data[4]), Float.valueOf(data[5]));
        DBMS.pgSQL.incProgramView(program);
    }
    public String[] getProgramCredits(int id){
        return DBMS.getPgSQL().searchSQL.getProgramCredits(id);
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

    public String[] sortViewesPrograms() {
        return DBMS.pgSQL.searchSQL.sortViewesPrograms();
    }

    public String[] sortViewesCast() {
        return DBMS.pgSQL.searchSQL.sortViewesCast();
    }

    public String[] sortRatesPrograms() {
        return DBMS.pgSQL.searchSQL.sortRatesPrograms();
    }

    public String[] sortRatesCast() {
        return DBMS.pgSQL.searchSQL.sortRatesCast();
    }
}
