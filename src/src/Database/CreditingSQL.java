package Database;

public class CreditingSQL {

    public void addProgram(String name, int owner) {
        PGSQL.query("INSERT INTO program VALUES ('" + name + "', DEFAULT, " + owner + ", FALSE, 0, 0)");
    }

    public void addCast(String name, int owner) {
        PGSQL.query("INSERT INTO casts VALUES ('" + name + "', DEFAULT, " + owner + ", FALSE, 0, -1)");
    }

    public void removeCredit(int programid, int castid) {
        PGSQL.query("DELETE FROM credit WHERE program = " + programid + " AND castid = " + castid);
    }
}
