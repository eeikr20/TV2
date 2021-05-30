package Domain;

import Controller.MainFX;

public class Notification {

    public int newUpdateCount(int userID){
        return DBMS.pgSQL.notificationSQL.newUpdateCount(userID);
    }

    public void readUpdates(int userID) {
        DBMS.pgSQL.notificationSQL.readUpdates(userID);
    }

    public String[] getNotifications(int id){
        return DBMS.pgSQL.notificationSQL.getNotifications(id);
    }

    public void eraseUpdates(int userID) {
        DBMS.pgSQL.notificationSQL.eraseUpdates(userID);
    }

    public void addUpdate(String update, int userID) {
        DBMS.pgSQL.notificationSQL.addUpdate(update, userID);
    }

    public void updateAdmin(String msg, String adminName) {
        int adminID = DBMS.pgSQL.getID("SELECT id FROM users where  name = '" + adminName + "'");

        MainFX.db.notification.addUpdate(msg, adminID);
    }

}
