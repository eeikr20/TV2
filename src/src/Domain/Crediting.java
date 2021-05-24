package Domain;

import Controller.MainFX;

public class Crediting {
    Notification notification = new Notification();

    public void addProgram(){
        System.out.println("What is the name of your program?");
        String name = DBMS.scanner.nextLine();
        Program program = new Program(name);

        notification.updateAdmin("Producer:" + MainFX.db.currentCustomer.name + " has added a program: " + name + " you must verify.");
        //todo update to GUI
    }
    /*
    public HashMap<String, Program> getTempOwner() {
        return tempOwner;
    }

    public void addOwner(String name, Program program) {
        this.owner.put(name, program);

    }

    public void removeFromTempOwner(String name) {
        tempOwner.remove(name);
    }
    */
    public void createCast(){
        System.out.println("What is the name of the cast?");
        String name = DBMS.scanner.nextLine();
        Cast cast = new Cast (name);
        //tempCast.put(name, cast);
        //notification.updateAdmin("Domain.Producer:" + super.getName() + " has added a cast: " + name + " you must verify.");

        notification.updateAdmin("Producer:" + MainFX.db.currentCustomer.name + " has added a cast: " + name + " you must verify.");

        //DBMS.postgresDB.query("INSERT INTO program casts ('" + name + "', DEFAULT, " + DBMS.currentCustomer.id + ", 0)");

        //todo update to GUI
    }
    /*
    public HashMap<String, Cast> getTempCast() {
        return tempCast;
    }
    */
    public void addCast(){
        System.out.println("What is the name of the program?");
        String program= DBMS.scanner.nextLine();
        Program p;

        if(DBMS.pgSQL.sqlContains("select count(*) from program where name = '" + program + "'")==0){
            System.out.println("That program does not exist");
            return;
        }

        //int id = DBMS.postgresDB.getID("select id from program where name = '" + program +"'");

        if(MainFX.db.currentCustomer.id != DBMS.pgSQL.getID("select owner from program where name = '" + program +"'")){
            System.out.println("You do not have access to the program");
            return;
        }
        /*
        if (owner.containsKey(program)){
            p=owner.get(program);
        }
        else if(tempOwner.containsKey(program)){
            p=tempOwner.get(program);
        }
        else {
            System.out.println("You do not have access to the program");
            return;
        }
        */
        System.out.println("What is the name of the cast?");
        String cast= DBMS.scanner.nextLine();
        Cast c;
        if(DBMS.pgSQL.sqlContains("select count(*) from casts where name = '" + cast + "'")==0){
            System.out.println("That cast does not exist");
            return;
        }
        System.out.println(MainFX.db.currentCustomer.id);
        System.out.println(DBMS.pgSQL.getID("select owner from casts where name = '" + cast +"'"));
        System.out.println(DBMS.pgSQL.getVerification("select verified from casts where name = '" + cast +"'"));

        if(MainFX.db.currentCustomer.id != DBMS.pgSQL.getID("select owner from casts where name = '" + cast +"'") && !DBMS.pgSQL.getVerification("select verified from casts where name = '" + cast +"'")){
            System.out.println("You can not use this cast");
            return;
        }
        /*
        if (DB.casts.containsKey(cast)){
            c = DB.casts.get(cast);
        }
        else if(tempCast.containsKey(cast)){
            c = tempCast.get(cast);
        }
        else {
            System.out.println("That cast does not exist");
            return;
        }
        */
        System.out.println("What was the role of the cast?");
        String role =DBMS.scanner.nextLine();
        new Role(program, cast, role);
        //Role r = new Role (p, c, role);
        //p.addCredit(role, r);
        //c.addRole(program, r);

    }
}
