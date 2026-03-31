package tests;

import base.BaseTest;
import models.UpdateBookRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookTest extends BaseTest {

    @Test
    public void updateBook() {

        UpdateBookRequest req = new UpdateBookRequest();
        req.setUserId(AuthSession.userId);
        req.setIsbn("9781449331818");

        int statusCode = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .header("Content-Type", "application/json")
                .body(req)
                .put("/BookStore/v1/Books/9781449325862")
                .getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }
}