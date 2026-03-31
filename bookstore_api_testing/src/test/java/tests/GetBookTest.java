package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookTest extends BaseTest {

    @Test
    public void getBook() {

        int statusCode = given()
                .queryParam("ISBN", "9781449325862")
                .get("/BookStore/v1/Book")
                .getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }
}