
import org.junit.jupiter.api.Test;

import pojos.ResponseBody;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Chapter2Test {

    @Test
    public void requestZipCodeForPolandHas(){
        ResponseBody body =
                given().
                log().all().
                when().
                get("http://api.zippopotam.us/PL/00-001").
                as(ResponseBody.class);

        assertEquals(body.getCountryAbbreviation(), "PL");
        assertEquals(body.getPlaces().get(0).getPlaceName(), "Warszawa");

    }

    @Test
    public void requestZipCodeForPolandHasSize() {
        ResponseBody body =
                given().
                        log().all().
                        when().
                        get("http://api.zippopotam.us/PL/00-001").
                        as(ResponseBody.class);

        assertThat(body.getPlaces(), hasSize(1));
    }


}
