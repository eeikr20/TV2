import java.util.List;

public class Account extends User{
    // Atributes
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

    }

    // Methods
    public void runCommand(String input){
        System.out.println("her");
    }
    public void logout(){

    }

    public  void addToFavorites(){

    }
    public  void removeFromFavorites(){

    }
    public  void rateProgram(){

    }
    public  void comment(){

    }
}
