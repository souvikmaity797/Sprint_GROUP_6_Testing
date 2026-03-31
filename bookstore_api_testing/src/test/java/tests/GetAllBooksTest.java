package tests;

import base.BaseTest;
import models.BooksResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBooksTest extends BaseTest {

    @Test
    public void getAllBooks() {

        BooksResponse res = given()
                .header("Content-Type", "application/json")
                .get("/BookStore/v1/Books")
                .as(BooksResponse.class);

        Assert.assertTrue(res.getBooks().size() > 0, "No books returned");

        // Deep validation of first book
        BooksResponse.Book book = res.getBooks().get(0);
        Assert.assertNotNull(book.getIsbn());
        Assert.assertNotNull(book.getTitle());
        Assert.assertNotNull(book.getAuthor());
        Assert.assertNotNull(book.getPublisher());
        Assert.assertTrue(book.getPages() >= 0);
    }
}