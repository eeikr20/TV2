public class DBMS {
    //Attributes
    private User currentUser;
    private boolean run;

    //constructor
    public DBMS(){
        this.run = true;
        this.currentUser = new Visitor();
    }
    //Methods
    public void runCommand(){
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
}
