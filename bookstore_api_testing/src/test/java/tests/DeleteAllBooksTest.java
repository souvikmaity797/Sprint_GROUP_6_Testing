package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import models.DeleteBooksRequest;
import utils.ExtentTestManager;

public class DeleteAllBooksTest extends BaseTest {

    @Test
    public void deleteAllBooks() {
//      ExtentTestManager.startTest("Delete All Books");

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
}