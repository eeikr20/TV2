package Domain;

public class Notification {

    public void readUpdates(int userID) {
        DBMS.pgSQL.readUpdates(userID);
    }

    public void eraseUpdates(int userID) {
        DBMS.pgSQL.query("DELETE FROM updates WHERE userid = " + userID);
    }

    public void addUpdate(String update, int userID) {
        DBMS.pgSQL.query("INSERT INTO updates VALUES ('" + update + "'," + userID + ", FALSE);");
    }

    public void updateAdmin(String msg) {
        System.out.println("what is the name of your admin?");
        String name = DBMS.scanner.nextLine();

        if(DBMS.pgSQL.sqlContains("select count(*) from users where name = '" + name + "'")==0){
            System.out.println("That user does nor exist");
            return;
        }
        String foundType = DBMS.pgSQL.getPassword("select type from users where name = '" + name + "'");
        if(!foundType.equals("administrator")){
            System.out.println("That user is not a administrator");
            return;
        }
        int adminID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + name + "'");

        DBMS.currentCustomer.notification.addUpdate(msg, adminID);
    }

}
