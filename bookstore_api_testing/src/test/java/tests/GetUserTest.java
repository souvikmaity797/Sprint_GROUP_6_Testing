package tests;

import base.BaseTest;
import models.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUserTest extends BaseTest {

    @Test
    public void getUser() {

        UserResponse res = given()
                .header("Authorization", "Bearer " + AuthSession.token)
                .get("/Account/v1/User/" + AuthSession.userId)
                .as(UserResponse.class);

        Assert.assertEquals(res.getUserId(), AuthSession.userId);
        Assert.assertNotNull(res.getBooks());
    }
}