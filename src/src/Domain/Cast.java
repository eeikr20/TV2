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
    }
    public void addRole(String name, Role role){
        this.role.put(name, role);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Role> getRole() {
        return role;
    }
}

