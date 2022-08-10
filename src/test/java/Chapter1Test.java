import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Test;
import pojos.ResponseBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class Chapter1Test {

    @Test
    @Story("User tries to login the system with invalid username and invalid password.")

    public void requestZipCodeForPoland(){
        given().
                log().all().
        when().
                get("http://api.zippopotam.us/PL/00-001").
        then().log().body().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);
    }

    @Test
    public void requestZipCodeForPolandAssertBody(){
        given().
                log().all().
        when().
                get("http://api.zippopotam.us/PL/00-001").
        then().log().body().
                assertThat().
                body("places[0].'place name'", equalTo("Warszawa"));

    }

    @Test
    public void requestZipCodeForPolandDecerialization(){
        ResponseBody body = given().
                log().all().
        when().
                get("http://api.zippopotam.us/PL/00-001").
                as(ResponseBody.class);



        System.out.println(body.getCountry());

    }

}
