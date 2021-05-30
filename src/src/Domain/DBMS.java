package Domain;

import Database.PGSQL;

import java.util.Scanner;

public class DBMS {
    //Attributes
    public static Scanner scanner = new Scanner(System.in);
    public static PGSQL pgSQL = new PGSQL();

    public Customer currentCustomer = new Customer("", "", 1, "visitor");
    public Program currentProgram = new Program("");
    public Cast currentCast = new Cast("");
    public String at = "";
    public Login login = new Login();
    public Search search = new Search();
    public Verification verification = new Verification();
    public Crediting crediting = new Crediting();
    public Notification notification = new Notification();
    public Favorites favorites = new Favorites();

    //constructor
    public DBMS(){
        //this.run = true;
        //this.currentUser = new Visitor();
        //this.postgresDB = new PostgresDB();
        //this.currentCustomer = new Customer("", "", -1, "visitor");
            //initTest();
    }
    //Methods

    public static PGSQL getPgSQL() {
        return pgSQL;
    }


    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public void addToFavorites(int program){
        pgSQL.addToFavorites(currentCustomer.id, program);
    }

    public void initTest() {
        //Producer producer = new Producer("producer", "producer", "producer");
        //DB.users.put("producer", producer);
        pgSQL.query("INSERT INTO users VALUES ('producer','producer',-3,'producer');");

        //Administrator administrator = new Administrator("administrator", "admin", "admin");
        //DB.users.put("admin", administrator);
        pgSQL.query("INSERT INTO users VALUES ('admin','admin',-2,'administrator');");

        //Program p1 = new Program("Star Wars");
        //Program p2 = new Program("Lord of the Rings");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Star Wars', DEFAULT, -3, TRUE, 100, 8.5)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Schindlers List', DEFAULT, -3, TRUE, 10, 10)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Lord of the Rings', DEFAULT, -3, TRUE, 1000, 9)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Forrest Gump', DEFAULT, -3, FALSE, 1, 8.6)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('The Wolf of Wall Street', DEFAULT, -3, TRUE, 1, 8.4)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Indiana Jones', DEFAULT, -3, TRUE, 15, 8.8)");

        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Mark Hamill', DEFAULT, 1, TRUE, 15, 9.8)");

        //new Role("Star Wars", "Mark Hamill", "Luke Skywalker");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(2,2,'Luke Skywalker', TRUE)");
        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Harrison Ford', DEFAULT, 1, TRUE, 16, 9.7)");
        //System.out.println(DBMS.pgSQL.searchSQL.getCastID("Harrison Ford"));
        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Harrison Ford', DEFAULT, 1, TRUE, 5, 2)");
        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Mark Hamill', DEFAULT, 1, TRUE, 1, 1.0)");
        //new Role("Star Wars", "Harrison Ford", "Han Solo");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(2,3,'Han Solo', TRUE)");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(7,3,'Indiana Jones', TRUE)");

        //DB.programs.put("Star Wars", p1);
        //DB.programs.put("Lord of the Rings", p2);
    }
}
