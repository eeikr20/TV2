package Domain;

public class Search {
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
}
