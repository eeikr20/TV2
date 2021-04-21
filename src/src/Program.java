
import java.util.HashMap;
import java.util.List;

public class Program {
    //Attributes
    private int id;
    private String name;
    private HashMap<String, Role> credits;
    private int views;
    private float rating;
    private int ratingSum;
    private int ratingTimes;
    private List<String> comments;

    public Program(String name) {
        this.credits = new HashMap<>();
        this.views = 0;
        this.rating = -1;
        this.ratingSum = 0;
        this.ratingTimes = 0;
        this.name = name;
        this.id = DB.count;
        DB.count = DB.count + 1;

    }

    //Methods
     public void addCredit(){

     }

     public void removeCredit(){

     }
    public void getCredits(){

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
}
