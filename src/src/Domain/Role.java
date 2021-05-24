package Domain;

public class Role {
    /*
    Program program;
    Cast cast;
    String role;

    public Role(Program program, Cast cast, String role) {
        this.program = program;
        this.cast = cast;
        this.role = role;
    }
    */
    public Role(String program, String cast, String role) {
        int programID = DBMS.pgSQL.getID("select id from program where name = '" + program +"'");
        int castID = DBMS.pgSQL.getID("select id from casts where name = '" + cast +"'");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(" + programID + "," + castID + ",'" + role + "', FALSE)");
    }
}
