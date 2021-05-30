package Domain;

public class Customer {
    public String name;
    String password;
    public int id;
    public String type;

    Customer(String name, String password,  int id, String type){
        this.name = name;
        this.password = password;
        this.id = id;
        this.type = type;
        DBMS.pgSQL.query("INSERT INTO users VALUES ('" + name + "', '" + password + "', DEFAULT, '" + type +"')");
    }
    public void resetCustomer(String name, String password,  int id, String type){
        this.name = name;
        this.password = password;
        this.id = id;
        this.type = type;
    }
    public void addComment(String comment, int programId){
        DBMS.pgSQL.addComment(comment, id, programId);
    }

    public String[] getHistory(int id) {
        return DBMS.pgSQL.getHistory(id);
    }

}
