import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.ResponseBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Request and response specifications tests")
@Feature("Request and response specifications")
public class RequestAndResponseSpecificationTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;


    @BeforeAll
    @DisplayName("Added RestAssured filter")
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                addFilter(new AllureRestAssured()).
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @BeforeAll
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    @DisplayName("Request zip code for Poland")
    @Story("Request specification")
    public void requestZipCodeForPoland() {
        given().
                spec(requestSpec).
                log().all().
                when().
                get("/PL/00-001").
                then().log().body().
                spec(responseSpec).
                assertThat().
                spec(responseSpec);
    }

    @Test
    @DisplayName("Request zip code for Poland and assert response body")
    @Story("Response specification")
    public void requestZipCodeForPolandAssertBody() {
        given().
                spec(requestSpec).
                log().all().
                when().
                get("PL/00-001").
                then().log().body().
                spec(responseSpec).
                assertThat().
                body("places[0].'place name'", equalTo("Warszawa"));

    }

    @Test
    @DisplayName("Request zip code for Poland and deserialize response")
    @Story("Request specification")
    public void requestZipCodeForPolandDeserialization() {
        ResponseBody body = given().
                spec(requestSpec).
                log().all().
                when().
                get("PL/00-001").
                as(ResponseBody.class);

        System.out.println(body.getCountry());
    }
}
