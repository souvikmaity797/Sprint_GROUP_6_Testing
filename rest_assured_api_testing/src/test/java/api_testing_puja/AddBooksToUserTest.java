package api_testing_puja;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddBooksToUserTest {

    public static void main(String[] args) {

        String userId = "YOUR_USER_ID";
        String token = "YOUR_BEARER_TOKEN";

        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        // JSON body as String
        String requestBody = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    { \"isbn\": \"9781449325862\" },\n" +
                "    { \"isbn\": \"9781449331818\" }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/BookStore/v1/Books");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}