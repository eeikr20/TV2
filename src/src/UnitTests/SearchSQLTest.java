package UnitTests;
/*
import Controller.MainFX;
import Database.SearchSQL;
import Domain.DBMS;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
*/
public class SearchSQLTest {
    /*
    DBMS d = new DBMS();
    private SearchSQL searchSQL = d.pgSQL.searchSQL;

    @BeforeEach
    public void setUp(){
        System.out.println("In search test");
    }

    @Test
    @DisplayName("testViewAllPrograms")
    public void testViewAllPrograms(){
        String[] correct = new String[] {"Star Wars", "Schindlers List", "Lord of the Rings", "The Wolf of Wall Street", "Indiana Jones"};
        String[] test = searchSQL.viewAllPrograms();
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testViewAllCast")
    public void testViewAllCast(){
        String[] correct = new String[] {"Mark Hamill | 2", "Harrison Ford | 3", "Harrison Ford | 4", "Mark Hamill | 5"};
        String[] test = searchSQL.viewAllCast();
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testViewPrograms")
    public void testViewPrograms(){
        String[] correct = new String[] {"Star Wars", "Schindlers List", "The Wolf of Wall Street"};
        String[] test = searchSQL.viewPrograms("st");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("testViewCast")
    public void testViewCast(){
        String[] correct = new String[] {"Mark Hamill | 2", "Mark Hamill | 5"};
        String[] test = searchSQL.viewCast("m");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("testSortViewesPrograms")
    public void testSortViewesPrograms(){
        String[] correct = new String[] {"Lord of the Rings", "Star Wars", "Indiana Jones", "Schindlers List", "The Wolf of Wall Street"};
        String[] test = searchSQL.sortViewesPrograms();
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testSortViewesCast")
    public void testSortViewesCast(){
        String[] correct = new String[] {"Mark Hamill | 2", "Harrison Ford | 3", "Harrison Ford | 4", "Mark Hamill | 5"};
        String[] test = searchSQL.viewAllCast();
        for(int i = 0; i < test.length; i++){
            System.out.println(test[i]);
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testSortRatesPrograms")
    public void testSortRatesPrograms(){
        String[] correct = new String[] {"Schindlers List", "Lord of the Rings", "Indiana Jones", "Star Wars", "The Wolf of Wall Street"};
        String[] test = searchSQL.sortRatesPrograms();
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testSortRatesCast")
    public void testSortRatesCast(){
        String[] correct = new String[] {"Mark Hamill | 2", "Harrison Ford | 3", "Harrison Ford | 4", "Mark Hamill | 5"};
        String[] test = searchSQL.sortRatesCast();
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("testViewProgramCredits")
    public void testViewProgramCredits(){
        String[] correct = new String[] {"Star Wars", "2", "-3", "true", "100", "8.5"};
        String[] test = searchSQL.viewProgramCredits(2);
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testGetProgramCredits")
    public void testGetProgramCredits(){
        String[] correct = new String[] {"Mark Hamill #2 | Luke Skywalker", "Harrison Ford #3 | Han Solo"};
        String[] test = searchSQL.getProgramCredits(2);
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("Search for the name of a cast member in the database, given the cast members id")
    public void testGetCastName(){
        assertEquals("Mark Hamill", searchSQL.getCastName(2));
    }

    @Test
    @DisplayName("Search for the id of a cast member in the database, given the cast members name")
    public void testGetCastID(){
        assertEquals(2, searchSQL.getCastID("Mark Hamill"));
    }


    @Test
    @DisplayName("testGetProgramName")
    public void testGetProgramName(){
        System.out.println(2);
        assertEquals("Star Wars", searchSQL.getProgramName(2));
    }

    @Test
    @DisplayName("testViewCastCredits")
    public void testViewCastCredits(){
        String[] correct = new String[] {"Mark Hamill", "2", "1", "true", "15", "9.8"};
        String[] test = searchSQL.viewCastCredits(2);
        for(int i = 0; i < test.length; i++){
            assertEquals(correct[i], test[i]);
        }
    }

    @Test
    @DisplayName("testGetCastCredits")
    public void testGetCastCredits(){
        String[] correct = new String[] {"Star Wars | Han Solo", "Indiana Jones | Indiana Jones"};
        String[] test = searchSQL.getCastCredits(3);
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("testGetComments")
    public void testGetComments(){
        String[] correct = new String[] {};
        String[] test = searchSQL.getComments(1);
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
        //todo add comments to db
    }

    @Test
    @DisplayName("testGetUserName")
    public void testGetUserName(){
        assertEquals("producer", searchSQL.getUserName(-3));
    }

    @Test
    @DisplayName("testViewMyCast")
    public void testViewMyCast(){
        String[] correct = new String[] {"Star Wars", "Schindlers List", "Forrest Gump", "The Wolf of Wall Street"};
        String[] test = searchSQL.viewMyCast("producer");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
        //todo add casts that only can be seen by producer
    }

    @Test
    @DisplayName("testViewAdminPrograms")
    public void testViewAdminPrograms(){
        String[] correct = new String[] {"Star Wars", "Schindlers List", "Forrest Gump", "The Wolf of Wall Street"};
        String[] test = searchSQL.viewAdminPrograms("st");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
        //todo add programs that only can be seen by admins
    }

    @Test
    @DisplayName("testViewMyPrograms")
    public void testViewMyPrograms(){
        String[] correct = new String[] {"Star Wars", "Schindlers List", "Forrest Gump", "The Wolf of Wall Street"};
        d.login.login("producer", "producer");
        String[] test = searchSQL.viewMyPrograms("st");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
        //todo add programs that only can be seen by producer
    }

    @Test
    @DisplayName("testGetAllAdmins")
    public void testGetAllAdmins(){
        String[] correct = new String[] {"admin"};
        String[] test = searchSQL.getAllAdmins();
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
    }

    @Test
    @DisplayName("testViewAdminCast")
    public void testViewAdminCast(){
        String[] correct = new String[] {"Mark Hamill | 2", "Mark Hamill | 5"};
        String[] test = searchSQL.viewAdminCast("m");
        int i = 0;
        while(test[i] != null){
            assertEquals(correct[i], test[i]);
            i = i + 1;
        }
        //todo add cast that only can be seen by admins
    }

     */
}