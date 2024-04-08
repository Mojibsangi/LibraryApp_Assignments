package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US01StepDefs {
    String actualUserCount;
    List<String > actualColumnsNames;


    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Create DB connection
        // DB_Util.createConnection();
        System.out.println("-----------------------------------------");
        System.out.println("------    DB CONN IS DONE BY HOOK -------");
        System.out.println("-----------------------------------------");


    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query="select  count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount = " + expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);


        // Close DB Conn
        // DB_Util.destroy();

        System.out.println("-----------------------------------------");
        System.out.println("------    DB CONN IS CLOSED BY HOOK -------");
        System.out.println("-----------------------------------------");

    }



    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query="SELECT * FROM  users where false";
        DB_Util.runQuery(query);

        //DB_Util.getAllColumnNamesAsList();



        actualColumnsNames = DB_Util.getAllColumnNamesAsList();  // to do this without DB_Util go to P01_JDBCIntro class in jdbc-project

        /*
        in order to do all the above part without using DB_Util, I would need the below steps

         // DriverManager class helps to generate connection with related database
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        // it helps to run queries
        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // will help us to store data that we are getting
        ResultSet rs = stmnt.executeQuery("SELECT * FROM  users WHERE false");

         */

        System.out.println("actualColumnsNames DB = " + actualColumnsNames);

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnsNames) {


        System.out.println("expectedColumnsNames Doc = " + expectedColumnsNames);


        Assert.assertEquals(expectedColumnsNames,actualColumnsNames);

    }







}
