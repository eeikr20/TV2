
import java.util.List;

public class Producer extends Account {
    //Attributes
    private List<String> updates;
    private List<String> owner;

    public Producer(String type, String name, String password){
        super(type, name, password);
    }

    //Methods
    public void addProgram(String name){
        System.out.println();
    }

    //@Override
    public void runCommand(String input){


    }
    public void addprogram(){



    }
    public void createProgram(){


    }
    public void addCast(){

    }
    public void deleteUpdates(){


    }
    public void runCommand(){
    }

    public List<String> getUpdates() {
        return updates;
    }
}
