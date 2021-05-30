package Domain;

import Controller.MainFX;

import java.util.HashMap;
import java.util.List;

public class Program {
    //Attributes
    private String name;
    private int id;
    private int owner;
    private boolean verified;
    private int views;
    private float avgRating;
    private HashMap<String,Role> credits;
    //private int ratingSum;
    //private int ratingTimes;
    private List<String> comments;

    public Program(String name) {
        //this.credits = new HashMap<String,Role>();
        //this.views = 0;
        //this.rating = -1;
        //this.ratingSum = 0;
        //this.ratingTimes = 0;
        //this.name = name;
        //this.id = DB.count;
        //DB.count = DB.count + 1;

        DBMS.pgSQL.query("INSERT INTO program VALUES ('" + name + "', DEFAULT, " + 1 + ", FALSE, 0, 0)");
    }
    public void setProgram(String name, int id, int owner, boolean verified, int views, float avgRating){
        this.name = name;
        this.id = id;
        this.owner = owner;
        this.verified = verified;
        this.views = views;
        this.avgRating = avgRating;
    }

    public int getId() {
        return id;
    }

    public int getOwner() {
        return owner;
    }

    public int getViews() {
        return views;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public HashMap<String, Role> getCredits(){
        return credits;
    }

    //Methods
     public void addCredit(String name, Role role){
        credits.put(name, role);
     }

     public void removeCredit(){

     }
    public void getRating(){

    }
    public void incViews(){

    }
    public void calculateRatings(){
        
    }

    public String getName() {
        return name;
    }

    public void addRating(int rate, int userid) {
        DBMS.pgSQL.addRating(rate, userid, id);
        Float newRating = DBMS.pgSQL.calculateRating(id);
        DBMS.pgSQL.setRating(id, newRating);
        this.avgRating = newRating;
    }
}
