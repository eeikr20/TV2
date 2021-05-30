package Domain;

import Controller.MainFX;

public class Crediting {
    Notification notification = new Notification();

    public void addProgram(String name, String admin){
        //Program program = new Program(name);
        DBMS.pgSQL.creditingSQL.addProgram(name, MainFX.db.currentCustomer.id);

        notification.updateAdmin("Producer:" + MainFX.db.currentCustomer.name + " has added a program: " + name + " you must verify.", admin);

    }
    public void createCast(String name, String admin){
        DBMS.pgSQL.creditingSQL.addCast(name, MainFX.db.currentCustomer.id);
        Cast cast = new Cast (name);

        notification.updateAdmin("Producer:" + MainFX.db.currentCustomer.name + " has added a cast: " + name + " you must verify.", admin);
    }

    public void removeCredit(int programid, int castid) {
        DBMS.pgSQL.creditingSQL.removeCredit(programid, castid);
    }
}
