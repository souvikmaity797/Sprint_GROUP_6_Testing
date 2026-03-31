package tests;

import base.BaseTest;
import models.AddBooksRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class AddBooksToUserTest extends BaseTest {

    @Test
    public void addBooks() {

        AddBooksRequest req = new AddBooksRequest();
        req.setUserId(AuthSession.userId);

        req.setCollectionOfIsbns(Arrays.asList(
                new AddBooksRequest.Isbn("9781449325862"),
                new AddBooksRequest.Isbn("9781449331818")
        ));

        int statusCode = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .header("Content-Type", "application/json")
                .body(req)
                .post("/BookStore/v1/Books")
                .getStatusCode();

        Assert.assertEquals(statusCode, 201);
    }
}