package api_testing_puja;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {

    public static void main(String[] args) {

        String userId = "YOUR_USER_ID_HERE";
        String token = "YOUR_BEARER_TOKEN_HERE";

        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get("/Account/v1/User/" + userId);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
    }
}
