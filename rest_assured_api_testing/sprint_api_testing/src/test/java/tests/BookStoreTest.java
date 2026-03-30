package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentManager;
import utils.ExtentTestManager;

import static io.restassured.RestAssured.given;

public class BookStoreTest extends BaseTest {

    String userId;
    String token;

    @Test(priority = 1)
    public void createUser() {
        ExtentTestManager.startTest("Create User");

        UserRequest req = new UserRequest();
        req.setUserName("souvk123");
        req.setPassword("Password@123");

        Response res = given()
                .header("Content-Type", "application/json")
                .body(req)
                .post("/Account/v1/User");

        Assert.assertEquals(res.statusCode(), 201);

        userId = res.jsonPath().getString("userID");

        ExtentTestManager.test.pass("User created: " + userId);
    }

    @Test(priority = 2)
    public void generateToken() {
        ExtentTestManager.startTest("Generate Token");

        TokenRequest req = new TokenRequest();
        req.setUserName("souvk123");
        req.setPassword("Password@123");

        TokenResponse res = given()
                .header("Content-Type", "application/json")
                .body(req)
                .post("/Account/v1/GenerateToken")
                .as(TokenResponse.class);

        token = res.getToken();

        Assert.assertNotNull(token);

        ExtentTestManager.test.pass("Token generated");
    }

    @Test(priority = 3)
    public void authorizedUser() {
        ExtentTestManager.startTest("Authorized User");

        AuthRequest req = new AuthRequest();
        req.setUserName("souvk123");
        req.setPassword("Password@123");

        Response res = given()
                .header("Content-Type", "application/json")
                .body(req)
                .post("/Account/v1/Authorized");

        Assert.assertEquals(res.asString(), "true");

        ExtentTestManager.test.pass("User authorized");
    }

    @Test(priority = 4)
    public void deleteAllBooks() {
        ExtentTestManager.startTest("Delete All Books");

        DeleteBooksRequest req = new DeleteBooksRequest();
        req.setUserId(userId);

        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(req)
                .delete("/BookStore/v1/Books");

        Assert.assertEquals(res.statusCode(), 204);

        ExtentTestManager.test.pass("All books deleted");
    }

    @Test
    public void tearDown() {
        ExtentManager.getInstance().flush();
    }
}