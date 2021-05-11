package Domain;

public class User {
    private String type;

    public User(String type){
        this.type = type;
    }

    public void runCommand(String input){
        switch (input){
            case "view programs" -> viewPrograms();
            case "view casts" ->viewCast();
            case "view cast roles" ->viewCastCredits();
            case "view program credits" -> viewProgramCredits();
            default -> System.out.println("invalid input");
        }
    }
    public void viewCast(){
        DBMS.postgresDB.viewCast();
    }

    public void viewPrograms(){
        DBMS.postgresDB.viewPrograms();
    }
    public void viewProgramCredits(){
        System.out.println("What program do you want to view?");
        String program = DB.scanner.nextLine();
        if (!DB.programs.containsKey(program)){
            System.out.println("That program does not exist");
            return;
        }
        for(Role role : DB.programs.get(program).getCredits().values()){
            System.out.println(role.cast.getName() + " " + role.role);
        }
        //todo use db
    }

    public void viewCastCredits(){
        System.out.println("What cast do you want to view?");
        String cast = DB.scanner.nextLine();
        if (!DB.casts.containsKey(cast)){
            System.out.println("That cast does not exist");
            return;
        }
        for(Role role : DB.casts.get(cast).getRole().values()){
            System.out.println(role.program.getName() + " " + role.role);
        }
        //todo use db
    }

    public void searchPrograms(){

    }

    public void searchPeople(){

    }

    public void searchByViews(){

    }

    public void searchByRating(){

    }

    public String getType(){
        return type;
    }



}
