import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Places;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

@DisplayName("Simple get request tests")
public class SimpleRequestsTest {

    GetPlace places = new GetPlace();
    @Test
    @Story("User tries to login the system with invalid username and invalid password.")
    @DisplayName("Request zip code for Poland")
    public void requestZipCodeForPoland(){
        places.getPlaceByCode("/PL/00-001").
        then().log().body().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);
    }

    @Test
    @DisplayName("Request zip code for Poland and assert response body")
    public void requestZipCodeForPolandAssertBody(){
        places.getPlaceByCode("/PL/00-001").
        then().log().body().
                assertThat().
                body("places[0].'place name'", equalTo("Warszawa"));

    }

    @Test
    @DisplayName("Request zip code for Poland and deserialize response")
    public void requestZipCodeForPolandDeserialization(){
        Places placesResponse =
                places.getPlaceByCode("/PL/00-001").
        then().
                extract().
                as(Places.class);

        assertEquals(placesResponse.getPostCode(), "00-001");

    }
}
