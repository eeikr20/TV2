public class DBMS {
    //Attributes
    public static User currentUser;
    private boolean run;

    //constructor
    public DBMS(){
        this.run = true;
        this.currentUser = new Visitor();
    }
    //Methods
    public void runCommand(){
        initTest();
        while (run){
            System.out.println("Awaiting input");
            String s = DB.scanner.nextLine();
            if (s.equals("end")){
                run=false;
            }
            else {
                currentUser.runCommand(s);
            }
        }
        System.out.println("farvel");

    }
    private void viewPrograms(){

    }
    private void viewPeople(){

    }
    private void searchPrograms(){

    }
    private void searchPeople(){

    }
    private void searchByViews(){

    }
    private void searchByRating(){

    }
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void initTest() {
        Producer producer = new Producer("producer", "navn", "navn");
        DB.users.put("navn", producer);

        Administrator administrator = new Administrator("administrator", "name", "name");
        DB.users.put("name", administrator);
    }
}
