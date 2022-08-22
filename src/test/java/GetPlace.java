import io.restassured.response.Response;
import utils.Config;

import static io.restassured.RestAssured.given;

public class GetPlace {

    public Response getPlaceByCode(String placeCode){
        return  given().
                log().all().
                baseUri(Config.BASE_URL).
                when().
                get(placeCode);
    }
}
