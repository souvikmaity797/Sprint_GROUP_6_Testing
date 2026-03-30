package api_testing_puja;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllBooksTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .when()
                .get("/BookStore/v1/Books");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asPrettyString());

        // Basic validation
        if (response.getStatusCode() == 200) {
            System.out.println("Books fetched successfully!");
        } else {
            System.out.println("Failed to fetch books.");
        }
    }
}