import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.ResponseBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecificationTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;


    @BeforeAll
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
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
    public void requestZipCodeForPolandDecerialization() {
        ResponseBody body = given().
                spec(requestSpec).
                log().all().
                when().
                get("PL/00-001").
                as(ResponseBody.class);

        System.out.println(body.getCountry());
    }
}
