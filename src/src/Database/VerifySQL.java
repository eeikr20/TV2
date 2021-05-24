package Database;

import Domain.DBMS;

public class VerifySQL {
    public void verifyCredit(int programID, int castID, String role){
        DBMS.pgSQL.query("UPDATE credits SET verified=TRUE WHERE program = " + programID + " AND castid = " + castID + "AND role = " + role);
    }
}
