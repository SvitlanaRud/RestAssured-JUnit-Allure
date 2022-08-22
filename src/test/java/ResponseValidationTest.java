
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pojos.Places;
import utils.Config;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Response validations tests")
public class ResponseValidationTest {

    @Test
    @DisplayName("Request zip code for Poland and verify country and place name")
    public void requestZipCodeForPolandHas(){
        Places body =
                given().
                log().all().
                baseUri(Config.BASE_URL).
        when().
                get("/PL/00-001").
        then()
                .extract().
                as(Places.class);

        assertEquals(body.getCountryAbbreviation(), "PL");
        assertEquals(body.getPlaces().get(0).getPlaceName(), "Warszawa");

    }

    @Test
    @DisplayName("Request zip code for Poland and verify places size")
    public void requestZipCodeForPolandHasSize() {
        Places body =
                given().
                        log().all().
                        baseUri(Config.BASE_URL).
                when().
                        get("/PL/00-001").
                then().
                        extract().
                        as(Places.class);

        assertThat(body.getPlaces(), hasSize(1));
    }


}
