package Database;

public class VerifySQL {
    public void verifyCredit(int programID, int castID, String role){
        PGSQL.query("UPDATE credit SET verified=TRUE WHERE program = " + programID + " AND castid = " + castID + " AND role = '" + role + "'");
    }
}
