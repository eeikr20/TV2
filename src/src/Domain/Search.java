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
        if(DBMS.pgSQL.sqlContains("select count(*) from program where name = '" + program + "'")==0){
            System.out.println("That program does not exist");
            return;
        }
        int id = DBMS.pgSQL.getID("select id from program where name = '" + program +"'");

        String[] data = DBMS.pgSQL.searchSQL.viewProgramCredits(id);
        MainFX.db.currentProgram.setProgram(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), Integer.parseInt(data[4]), Float.parseFloat(data[5]));
        DBMS.pgSQL.incProgramView(program);
    }
    public String[] getProgramCredits(int id){
        return DBMS.getPgSQL().searchSQL.getProgramCredits(id);
    }
    public String[] getAllProgramCredits(int id){
        return DBMS.getPgSQL().searchSQL.getAllProgramCredits(id);
    }

    public String[] getCastCredits(int id) {
        return DBMS.getPgSQL().searchSQL.getCastCredits(id);
    }
    /*
    public String[] getAllCastCredits(int id) {
        return DBMS.getPgSQL().searchSQL.getAllCastCredits(id);
    }

    public void viewCastCredits(String cast){
        if(DBMS.pgSQL.sqlContains("select count(*) from casts where name = '" + cast + "'")==0){
            System.out.println("That cast does not exist");
            return;
        }
        int id = DBMS.pgSQL.getID("select id from casts where name = '" + cast +"'");

        String[] data = DBMS.pgSQL.searchSQL.viewCastCredits(id);
        for (String s : data){
            System.out.println(s);
        }
        MainFX.db.currentCast.setCast(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), Integer.parseInt(data[4]), Float.parseFloat(data[5]));
        DBMS.pgSQL.incCastView(id);

        DBMS.pgSQL.viewCastCredits("select * from credit where castid = " + id);
    }
    */
    public void viewCastCredits(int id){
        if(DBMS.pgSQL.sqlContains("select count(*) from casts where id = " + id)==0){
            System.out.println("That cast does not exist");
            return;
        }

        String[] data = DBMS.pgSQL.searchSQL.viewCastCredits(id);
        for (String s : data){
            System.out.println(s);
        }
        MainFX.db.currentCast.setCast(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), Integer.parseInt(data[4]), Float.parseFloat(data[5]));
        DBMS.pgSQL.incCastView(id);

        DBMS.pgSQL.viewCastCredits("select * from credit where castid = " + id);
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

    public String[] getComments(int id) {
        return DBMS.pgSQL.searchSQL.getComments(id);
    }

    public String[] viewMyCast(String input) {
        return DBMS.pgSQL.searchSQL.viewMyCast(input);
    }

    public String[] viewAdminPrograms(String input) {
        return DBMS.pgSQL.searchSQL.viewAdminPrograms(input);
    }

    public String[] viewMyPrograms(String input) {
        return DBMS.pgSQL.searchSQL.viewMyPrograms(input);
    }

    public String[] getAllAdmins() {
        return DBMS.pgSQL.searchSQL.getAllAdmins();
    }

    public String[] viewAdminCast(String input) {
        return DBMS.pgSQL.searchSQL.viewAdminCast(input);
    }
}
