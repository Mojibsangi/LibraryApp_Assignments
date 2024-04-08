package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03StepDefs {

    BookPage bookPage = new BookPage();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String Books) {

        bookPage.navigateModule("Books");


    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        bookPage.mainCategoryElement.click();


    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {


        List<String> actualBookCategoriesUI = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);

        System.out.println("actualBookCategoriesUI = " + actualBookCategoriesUI);

        actualBookCategoriesUI.remove("ALL");
        //System.out.println(" After All removed    actualBookCategoriesUI = " + actualBookCategoriesUI);


        String query="select name from book_categories";

         DB_Util.runQuery(query);

         List<String> expectedBookCategoriesDB= DB_Util.getColumnDataAsList("name");

        System.out.println("expectedBookCategoriesDB = " + expectedBookCategoriesDB);


        Assert.assertEquals(expectedBookCategoriesDB,actualBookCategoriesUI);

    }





}


/*

public class BooksPageStepDefs {

    BasePage basePage;
    List<String> categoriesFromUI;
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {

        basePage=new BookPage();
        basePage.navigateModule(module);


    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        Select select=new Select(((BookPage)basePage).mainCategoryElement);

        List<WebElement> options = select.getOptions();
         categoriesFromUI= BrowserUtil.getElementsText(options);

         categoriesFromUI.remove("ALL");
        System.out.println("categoriesFromUI = " + categoriesFromUI);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        DB_Util.runQuery("select name from book_categories");
        List<String> categoriesFromDB = DB_Util.getColumnDataAsList("name");
        System.out.println("categoriesFromDB = " + categoriesFromDB);

        Assert.assertEquals(categoriesFromUI,categoriesFromDB);
 */