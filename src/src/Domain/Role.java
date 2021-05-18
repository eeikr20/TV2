package Domain;

public class Role {
    Program program;
    Cast cast;
    String role;

    public Role(Program program, Cast cast, String role) {
        this.program = program;
        this.cast = cast;
        this.role = role;
        //todo get the ids for the correct program and cast
    }
    public Role(String program, String cast, String role) {
        int programID = DBMS.postgresDB.getID("select id from program where name = '" + program +"'");
        int castID = DBMS.postgresDB.getID("select id from casts where name = '" + cast +"'");
        DBMS.postgresDB.query("INSERT INTO credit VALUES(" + programID + "," + castID + ",'" + role + "')");
    }
}
