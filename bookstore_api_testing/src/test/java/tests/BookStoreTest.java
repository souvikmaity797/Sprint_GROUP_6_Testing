package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import utils.ExtentManager;
import utils.ExtentTestManager;

import static io.restassured.RestAssured.given;

public class BookStoreTest extends BaseTest {

    @Test(priority = 1)
    public void createUser() {
        ExtentTestManager.startTest("Create User");

        UserRequest req = new UserRequest();
        req.setUserName("xyz");
        req.setPassword("Password@123");
        Response res = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")   // ⭐ REQUIRED
                .body(req)
                .post("/Account/v1/User");


        Assert.assertEquals(res.statusCode(), 201);

       AuthSession.userId = res.jsonPath().getString("userID");

        ExtentTestManager.test.pass("User created: " + AuthSession.userId);
    }

    @Test(priority = 2)
    public void generateToken() {
        ExtentTestManager.startTest("Generate Token");

        TokenRequest req = new TokenRequest();
        req.setUserName("xyz");
        req.setPassword("Password@123");

        TokenResponse res = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(req)
                .post("/Account/v1/GenerateToken")
                .as(TokenResponse.class);

        AuthSession.token = res.getToken();

        Assert.assertNotNull(AuthSession.token);

        ExtentTestManager.test.pass("Token generated");
    }

    @Test(priority = 3)
    public void authorizedUser() {
        ExtentTestManager.startTest("Authorized User");

        AuthRequest req = new AuthRequest();
        req.setUserName("xyz");
        req.setPassword("Password@123");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(req)
                .post("/Account/v1/Authorized");

        Assert.assertEquals(res.asString(), "true");

        ExtentTestManager.test.pass("User authorized");
    }

    @Test(priority = 4)
    public void deleteAllBooks() {
        ExtentTestManager.startTest("Delete All Books");

        DeleteBooksRequest req = new DeleteBooksRequest();
        req.setUserId(AuthSession.userId);

        Response res = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .header("Content-Type", "application/json")
                .body(req)
                .delete("/BookStore/v1/Books");

        Assert.assertEquals(res.statusCode(), 204);

        ExtentTestManager.test.pass("All books deleted");
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.getInstance().flush();
    }
}