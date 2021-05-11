package Domain;

import java.util.List;

public class Account extends User{
    // Attributes
    private String name;
    private int id;
    private String password;
    private List<Integer> favorites;
    private List<Integer> history;

    public Account(String type, String name, String password){
        super(type);
        this.name = name;
        this.password = password;
        this.id = DB.count;
        DB.count = DB.count + 1;

        //DBMS.postgresDB.query("INSERT INTO users VALUES ('" + name + "','" + password + "'," + id + ",'" + type +  "');");
    }

    // Methods
    public void runCommand(String input){
        switch (input){
            case "logout" ->logout();
            case "add to favorite" ->addToFavorites();
            default -> super.runCommand(input);
        }
    }

    public void logout(){
        //todo remove old db
        DBMS.currentUser = new Visitor();
        DBMS.currentCustomer.resetCustomer("", "", 0, "visitor");
    }

    public  void addToFavorites(){

    }
    public  void removeFromFavorites(){

    }
    public  void rateProgram(){

    }
    public  void comment(){

    }
    public String getPassword(){
        return password;
    }
    public String getName() {
        return name;
    }
}
