package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

public class US06StepDefs {


    BookPage bookPage= new BookPage();
  //  String bookTitle;

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookTitle) {
       // this.bookTitle=bookTitle;

        bookPage.bookName.sendKeys(bookTitle);

    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String bookISBN) {

        bookPage.isbn.sendKeys(bookISBN);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String bookYear) {
        bookPage.year.sendKeys(bookYear);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String bookAuthor) {
        bookPage.author.sendKeys(bookAuthor);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategory) {

        Select select = new Select(bookPage.categoryDropdown);

       select.selectByVisibleText(bookCategory);
    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
       bookPage.saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String displayMessage) {

        BrowserUtil.waitForVisibility(bookPage.toastMessage,5);
        bookPage.toastMessage.isDisplayed();

    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String string) {

       // String actual=

    }





}
