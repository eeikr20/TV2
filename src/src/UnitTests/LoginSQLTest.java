package UnitTests;
/*
import Database.LoginSQL;
import Domain.DBMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
*/
class LoginSQLTest {
    /*
    DBMS d = new DBMS();
    private LoginSQL loginSQL = DBMS.pgSQL.loginSQL;

    @BeforeEach
    public void setUp(){
        System.out.println("In login test");
    }
    @Test
    @DisplayName("Search for the name of a user in the database, given the username")
    public void testUserInDB(){
        System.out.println(3);
        assertEquals(true, loginSQL.userInDB("admin"), "The user 'admin' should be in the database");
        assertEquals(false, loginSQL.userInDB("wrong"), "The user 'wrong' should not be in the database");
    }
    @Test
    @DisplayName("Search for a password given a username")
    public void testGetPassword(){
        assertEquals("admin", loginSQL.getPassword("admin"), "The password for user 'admin' should be 'admin'");
    }
    //setUserData
    @Test
    @DisplayName("Search for user data given a username")
    public void testSetUserData(){
        String[] correct = new String[] {"admin", "admin", "-2", "administrator"};
        String[] test = loginSQL.setUserData("admin");

        for(int i = 0; i < 4; i++){
            assertEquals(true, test[i].equals(correct[i]), "The user data for user 'admin' should be: 'admin', 'admin', '-2', 'administrator'");
        }
    }
    */
}
