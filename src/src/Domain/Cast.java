package Domain;

import java.util.HashMap;
import java.util.List;

public class Cast {
    //Attributes
    private HashMap<String, Role> role;
    private int id;
    private String name;
    private int views;
    private int ratings;
    private List<String> comments;

    //Methods

    public Cast (String name){
        this.name = name;
        this.id = DB.count;
        DB.count = DB.count + 1;
        role = new HashMap<>();
        this.views =0;
        this.ratings = -1;
        //todo get the id of the owner instread of 0 as id
        DBMS.postgresDB.query("INSERT INTO casts VALUES ('" + name + "', " + id + ", " + 0 + ", FALSE)");
    }
    public void addRole(String name, Role role){
        this.role.put(name, role);
        //todo get the id of the program instread of 0 as id
        DBMS.postgresDB.query("INSERT INTO credit VALUES (" + 0 +", " + id + ", '" + role +"')");
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Role> getRole() {
        return role;
    }
}

