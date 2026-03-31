package tests;

import base.BaseTest;
import models.DeleteBookRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookTest extends BaseTest {

    @Test
    public void deleteBook() {

        DeleteBookRequest req = new DeleteBookRequest();
        req.setUserId(AuthSession.userId);
        req.setIsbn("9781449331818");

        int statusCode = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .header("Content-Type", "application/json")
                .body(req)
                .delete("/BookStore/v1/Book")
                .getStatusCode();

        Assert.assertEquals(statusCode, 204);
    }
}