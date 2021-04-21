public class User {
    private String type;

    public User(String type){
        this.type = type;
    }

    public void runCommand(String input){
        switch (input){
            case "view program" ->viewPrograms();
            default -> System.out.println("invalid input");
        }
    }


    public void viewPrograms(){
        for(Program p : DB.programs.values()){
            System.out.println(p.getName());
        }
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
