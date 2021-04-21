
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Producer extends Account {
    //Attributes
    private ArrayList<String> updates;
    private ArrayList<String> newUpdates;
    private HashMap<String, Program> owner;
    private HashMap<String, Program> tempOwner;

    public Producer(String type, String name, String password){
        super(type, name, password);
        this.updates = new ArrayList<>();
        this.newUpdates = new ArrayList<>();
        this.owner = new HashMap<>();
        this.tempOwner = new HashMap<>();
    }

    //Methods

    //@Override
    public void runCommand(String input){
        switch (input){
            case "add program" ->addProgram();
            case "read updates" ->readUpdates();
            case "delete updates" ->eraseUpdates();
            default -> super.runCommand(input);
        }
    }

    public void addProgram(){
        System.out.println("What is the name of your program?");
        String name = DB.scanner.nextLine();
        Program program = new Program(name);

        updateAdmin("Producer:" + super.getName() + " has added a program: " + name + " you must verify.");
        tempOwner.put(name, program);
    }

    public void updateAdmin(String msg) {
        System.out.println("what is the name of your admin?");
        String name = DB.scanner.nextLine();

        if (!DB.users.containsKey(name)) {
            System.err.println("That admin does not exist.");
            return;
        }
        if (!DB.users.get(name).getType().equals("administrator")) {
            System.err.println("That admin does not exist.");
            return;
        }

        ((Administrator)DB.users.get(name)).addUpdate(msg);
    }

    public void createCast(){
    }
    public void addCast(){
    }

    public void addUpdate(String update) {
        updates.add(update);
        newUpdates.add(update);
    }

    public HashMap<String, Program> getTempOwner() {
        return tempOwner;
    }

    public void addOwner(String name, Program program) {
        this.owner.put(name, program);

    }

    public void removeFromTempOwner(String name) {
        tempOwner.remove(name);
    }

    public void readUpdates() {
        for (String update: updates) {
            System.out.println(update);
        }
        newUpdates.clear();
    }

    public void eraseUpdates() {
        newUpdates.clear();
        updates.clear();
    }
}
