package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02StepDefs {


    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBooksNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {

        loginPage.login(librarian);


    }


    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        BrowserUtil.waitForVisibility(dashBoardPage.borrowedBooksNumber, 5);

        actualBorrowedBooksNumber = dashBoardPage.borrowedBooksNumber.getText();

        System.out.println("actualBorrowedBooksNumber = " + actualBorrowedBooksNumber);


    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {




        String query = "select count(book_id) from book_borrow where is_returned = 0";

        DB_Util.runQuery(query);

        String expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedBorrowedBooksNumber = " + expectedBorrowedBooksNumber);

        Assert.assertEquals(expectedBorrowedBooksNumber, actualBorrowedBooksNumber);


    }

}








/*


    String globalBorrowedBooksNumberUI;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        loginPage = new LoginPage();
        loginPage.login(userType);

    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        dashBoardPage = new DashBoardPage();
        BrowserUtil.waitFor(5);
        String borrowedBooksNumberUI = dashBoardPage.borrowedBooksNumber.getText();
        globalBorrowedBooksNumberUI = borrowedBooksNumberUI;


    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        String borrowedBooksNumberDB = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(globalBorrowedBooksNumberUI, borrowedBooksNumberDB);

 */