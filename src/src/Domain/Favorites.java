package Domain;

import Controller.MainFX;

public class Favorites {
    public void add(int userID, int programID){
        DBMS.pgSQL.favoritesSQL.add(userID, programID);
    }
    public void remove(int userID, String program){
        DBMS.pgSQL.favoritesSQL.remove(userID, program);
    }
    public String[] get(int userID){
        return DBMS.pgSQL.favoritesSQL.get(userID);
    }
}
