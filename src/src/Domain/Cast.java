package Domain;

import Controller.MainFX;

import java.util.HashMap;
import java.util.List;

public class Cast {
    //Attributes
    private HashMap<String, Role> role;
    private String name;
    private int id;
    private int owner;
    private boolean verified;
    private float avgRating;
    private int views;
    private int ratings;
    private List<String> comments;

    //Methods

    public Cast (String name){
        this.name = name;
        //this.id = DB.count;
        //DB.count = DB.count + 1;
        role = new HashMap<>();
        this.views =0;
        this.ratings = -1;

        DBMS.pgSQL.query("INSERT INTO casts VALUES ('" + name + "', DEFAULT, " + 1 + ", FALSE, 0, -1)");
    }
    public void setCast(String name, int id, int owner, boolean verified, int views, float avgRating){
        this.name = name;
        this.id = id;
        this.owner = owner;
        this.verified = verified;
        this.views = views;
        this.avgRating = avgRating;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Role> getRole() {
        return role;
    }

    public int getViews() {
        return views;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public int getId() {
        return id;
    }
}

