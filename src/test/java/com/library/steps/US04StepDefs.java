package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class US04StepDefs {


    BookPage bookPage = new BookPage();
    String bookName;


    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        this.bookName=bookName;

        bookPage.search.sendKeys(bookName, Keys.ENTER );


    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

        bookPage.editBook("The Way of Kings").click();




    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        System.out.println("\n-----------------------ACTUAL FROM UI-----------------------\n");


        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        String [] allActual = {actualBookName,actualAuthorName,actualISBN,actualYear,actualDesc};

        System.out.println("Array of all Actual UI: "+Arrays.toString(allActual));

        String query="select * from books where name ='"+bookName+"'";

        DB_Util.runQuery(query);


        System.out.println("\n-----------------------EXPECTED FROM DB-----------------------\n");

        Map<String,String > expectedBookInfo= DB_Util.getRowMap(1);

         String expectedBookName = expectedBookInfo.get("name");
         String expectedAuthorName =expectedBookInfo.get("author");
         String expectedISBN = expectedBookInfo.get("isbn");
         String expectedYear = expectedBookInfo.get("year");
         String expectedDesc = expectedBookInfo.get("description");

        String [] allExpected = {expectedBookName,expectedAuthorName,expectedISBN,expectedYear,expectedDesc};
        System.out.println("Array of all Expected DB: " + Arrays.toString(allExpected));



        Assert.assertArrayEquals(allExpected,allActual);

    }



}



/*
        System.out.println();
        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedAuthorName,actualAuthorName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedDesc,actualDesc);

 */

