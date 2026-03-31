package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {

    @Test
    public void deleteUser() {

        int statusCode = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .delete("/Account/v1/User/" + AuthSession.userId)
                .getStatusCode();

        Assert.assertEquals(statusCode, 204);
    }
}