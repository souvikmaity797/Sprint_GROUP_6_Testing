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
      String userId = AuthSession.userId;
      
      Response res = given()
              .header("Authorization", "Bearer " + AuthSession.token)
              .queryParam("UserId", userId)   
              .when()
              .delete("/BookStore/v1/Books");

      System.out.println("Status Code: " + res.getStatusCode());
      System.out.println("TOKEN: " + AuthSession.token);
      System.out.println("TOKEN: " + AuthSession.userId);
      Assert.assertEquals(res.statusCode(), 204);

      ExtentTestManager.test.pass("All books deleted");
  }
}