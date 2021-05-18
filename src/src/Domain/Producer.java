package Domain;

public class Producer {
    /*
    //Attributes
    private ArrayList<String> updates;
    private ArrayList<String> newUpdates;
    private HashMap<String, Program> owner;
    private HashMap<String, Program> tempOwner;
    private HashMap<String, Cast> tempCast;

    public Producer(String type, String name, String password){
        super(type, name, password);
        this.updates = new ArrayList<>();
        this.newUpdates = new ArrayList<>();
        this.owner = new HashMap<>();
        this.tempOwner = new HashMap<>();
        this.tempCast= new HashMap<>();
    }

    //Methods

    //@Override
    public void runCommand(String input){
        switch (input){
            case "add program" ->addProgram();
            case "read updates" ->readUpdates();
            case "delete updates" ->eraseUpdates();
            case "create cast" ->createCast();
            case "add cast" ->addCast();
            default -> super.runCommand(input);
        }
    }

    public void addProgram(){
        System.out.println("What is the name of your program?");
        String name = DB.scanner.nextLine();
        Program program = new Program(name);

        updateAdmin("Domain.Producer:" + super.getName() + " has added a program: " + name + " you must verify.");
        tempOwner.put(name, program);
        //todo remove usage of old db
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

    private void createCast(){
        System.out.println("What is the name of the cast?");
        String name = DB.scanner.nextLine();
        Cast cast = new Cast (name);
        tempCast.put(name, cast);
        updateAdmin("Domain.Producer:" + super.getName() + " has added a cast: " + name + " you must verify.");
        //todo remove usage of old db
    }

    public HashMap<String, Cast> getTempCast() {
        return tempCast;
    }

    private void addCast(){
        System.out.println("What is the name of the program?");
        String program= DB.scanner.nextLine();
        Program p;
        if (owner.containsKey(program)){
            p=owner.get(program);
        }
        else if(tempOwner.containsKey(program)){
            p=tempOwner.get(program);
        }
        else {
            System.out.println("You do not have access to the program");
            return;
        }
        System.out.println("What is the name of the cast?");
        String cast= DB.scanner.nextLine();
        Cast c;
        if (DB.casts.containsKey(cast)){
            c = DB.casts.get(cast);
        }
        else if(tempCast.containsKey(cast)){
            c = tempCast.get(cast);
        }
        else {
            System.out.println("That cast does not exist");
            return;
        }
        System.out.println("What was the role of the cast?");
        String role =DB.scanner.nextLine();
        Role r = new Role (p, c, role);
        p.addCredit(role, r);
        c.addRole(program, r);
    }
*/
}
