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
        DBMS.postgresDB.query("INSERT INTO credit VALUES(0,0,'" + role + "')");
    }
}
